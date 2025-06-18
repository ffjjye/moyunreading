package com.moyun.service;

import com.moyun.entity.Book;
import com.moyun.entity.Comment;
import com.moyun.entity.ReadingNote;
import com.moyun.entity.User;
import java.util.List;

public interface AdminService {
    // 用户管理
    List<User> getAllUsers(Integer adminId);
    void deleteUser(Integer adminId, Integer targetUserId);

    // 书籍管理
    List<Book> getAllBooks(Integer adminId);
    void deleteBook(Integer adminId, Integer bookId);

    // 笔记管理
    List<ReadingNote> getAllNotes(Integer adminId);
    void deleteNote(Integer adminId, Integer noteId);

    // 评论管理
    List<Comment> getAllComments(Integer adminId);
    void deleteComment(Integer adminId, Integer commentId);
} 