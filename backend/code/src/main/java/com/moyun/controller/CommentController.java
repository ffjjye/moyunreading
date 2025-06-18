package com.moyun.controller;

import com.moyun.common.Result;
import com.moyun.dto.CommentDTO;
import com.moyun.entity.Comment;
import com.moyun.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Result<Comment> createComment(@RequestBody CommentDTO commentDTO, HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            Comment comment = commentService.createComment(commentDTO, userId);
            return Result.success("评论成功", comment);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/target/{targetId}")
    public Result<List<Comment>> getComments(@PathVariable Integer targetId) {
        try {
            List<Comment> comments = commentService.getComments(targetId);
            return Result.success(comments);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/{commentId}")
    public Result<Void> deleteComment(@PathVariable Integer commentId, HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            commentService.deleteComment(commentId, userId);
            return Result.success("删除评论成功", null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public Result<List<Comment>> getUserComments(@PathVariable Integer userId) {
        try {
            List<Comment> comments = commentService.getUserComments(userId);
            return Result.success(comments);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
} 