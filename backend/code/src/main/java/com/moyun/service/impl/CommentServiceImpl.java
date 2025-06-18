package com.moyun.service.impl;

import com.moyun.dto.CommentDTO;
import com.moyun.entity.Comment;
import com.moyun.entity.User;
import com.moyun.mapper.CommentMapper;
import com.moyun.mapper.UserMapper;
import com.moyun.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public Comment createComment(CommentDTO commentDTO, Integer userId) {
        // 检查用户是否存在
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 检查评论类型是否合法
        if (!"NOTE_COMMENT".equals(commentDTO.getType()) && !"COMMENT_REPLY".equals(commentDTO.getType())) {
            throw new RuntimeException("非法的评论类型");
        }

        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setTargetId(commentDTO.getTargetId());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setCreateTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());

        commentMapper.insert(comment);
        return comment;
    }

    @Override
    public List<Comment> getComments(Integer targetId) {
        return commentMapper.findByTargetId(targetId);
    }

    @Override
    @Transactional
    public void deleteComment(Integer commentId, Integer userId) {
        // 检查评论是否存在
        Comment comment = commentMapper.findById(commentId);
        if (comment == null) {
            throw new RuntimeException("评论不存在");
        }

        // 检查是否有权限删除（评论作者或管理员）
        User user = userMapper.findById(userId);
        if (!comment.getUserId().equals(userId) && !"ROLE_ADMIN".equals(user.getRole())) {
            throw new RuntimeException("没有权限删除此评论");
        }

        commentMapper.deleteById(commentId);
    }

    @Override
    public List<Comment> getUserComments(Integer userId) {
        return commentMapper.findByUserId(userId);
    }
} 