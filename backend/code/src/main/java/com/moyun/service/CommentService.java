package com.moyun.service;

import com.moyun.dto.CommentDTO;
import com.moyun.entity.Comment;
import java.util.List;

public interface CommentService {
    Comment createComment(CommentDTO commentDTO, Integer userId);
    List<Comment> getComments(Integer targetId);
    void deleteComment(Integer commentId, Integer userId);
    List<Comment> getUserComments(Integer userId);
} 