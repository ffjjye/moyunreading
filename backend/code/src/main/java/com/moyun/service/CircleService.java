package com.moyun.service;

import com.moyun.dto.CircleDTO;
import com.moyun.entity.Circle;
import com.moyun.entity.User;
import java.util.List;

public interface CircleService {
    Circle createCircle(CircleDTO circleDTO, Integer creatorId);
    Circle getMentorCircle(Integer mentorId);
    List<User> getCircleMembers(Integer circleId);
    void joinCircle(Integer circleId, Integer userId);
    void leaveCircle(Integer circleId, Integer userId);
} 