package com.moyun.service.impl;

import com.moyun.dto.CircleDTO;
import com.moyun.entity.Circle;
import com.moyun.entity.User;
import com.moyun.mapper.CircleMapper;
import com.moyun.mapper.UserMapper;
import com.moyun.service.CircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CircleServiceImpl implements CircleService {

    @Autowired
    private CircleMapper circleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public Circle createCircle(CircleDTO circleDTO, Integer creatorId) {
        // 检查创建者是否为导师
        User creator = userMapper.findById(creatorId);
        if (creator == null || !"ROLE_TEACHER".equals(creator.getRole())) {
            throw new RuntimeException("只有导师才能创建圈子");
        }

        Circle circle = new Circle();
        circle.setName(circleDTO.getName());
        circle.setDescription(circleDTO.getDescription());
        circle.setCreatorId(creatorId);
        circle.setMentorId(creatorId);
        circle.setCreateTime(LocalDateTime.now());
        circle.setUpdateTime(LocalDateTime.now());

        circleMapper.insert(circle);
        return circle;
    }

    @Override
    public Circle getMentorCircle(Integer mentorId) {
        return circleMapper.findByMentorId(mentorId);
    }

    @Override
    public List<User> getCircleMembers(Integer circleId) {
        return userMapper.findByCircleId(circleId);
    }

    @Override
    @Transactional
    public void joinCircle(Integer circleId, Integer userId) {
        // 检查用户是否已经是圈子成员
        if (circleMapper.isMember(circleId, userId)) {
            throw new RuntimeException("已经是圈子成员");
        }

        // 检查用户是否为学生
        User user = userMapper.findById(userId);
        if (user == null || !"ROLE_STUDENT".equals(user.getRole())) {
            throw new RuntimeException("只有学生才能加入圈子");
        }

        circleMapper.addMember(circleId, userId);
    }

    @Override
    @Transactional
    public void leaveCircle(Integer circleId, Integer userId) {
        // 检查用户是否是圈子成员
        if (!circleMapper.isMember(circleId, userId)) {
            throw new RuntimeException("不是圈子成员");
        }

        circleMapper.removeMember(circleId, userId);
    }
} 