package com.moyun.controller;

import com.moyun.common.Result;
import com.moyun.entity.Book;
import com.moyun.entity.Comment;
import com.moyun.entity.ReadingNote;
import com.moyun.entity.User;
import com.moyun.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 用户管理
    @GetMapping("/users")
    public Result<List<User>> getAllUsers(HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            List<User> users = adminService.getAllUsers(userId);
            return Result.success(users);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/user/{targetUserId}")
    public Result<Void> deleteUser(@PathVariable Integer targetUserId, HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            adminService.deleteUser(userId, targetUserId);
            return Result.success("删除用户成功", null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    // 书籍管理
    @GetMapping("/books")
    public Result<List<Book>> getAllBooks(HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            List<Book> books = adminService.getAllBooks(userId);
            return Result.success(books);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/book/{bookId}")
    public Result<Void> deleteBook(@PathVariable Integer bookId, HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            adminService.deleteBook(userId, bookId);
            return Result.success("删除书籍成功", null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    // 笔记管理
    @GetMapping("/notes")
    public Result<List<ReadingNote>> getAllNotes(HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            List<ReadingNote> notes = adminService.getAllNotes(userId);
            return Result.success(notes);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/note/{noteId}")
    public Result<Void> deleteNote(@PathVariable Integer noteId, HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            adminService.deleteNote(userId, noteId);
            return Result.success("删除笔记成功", null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    // 评论管理
    @GetMapping("/comments")
    public Result<List<Comment>> getAllComments(HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            List<Comment> comments = adminService.getAllComments(userId);
            return Result.success(comments);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/comment/{commentId}")
    public Result<Void> deleteComment(@PathVariable Integer commentId, HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            adminService.deleteComment(userId, commentId);
            return Result.success("删除评论成功", null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
} 