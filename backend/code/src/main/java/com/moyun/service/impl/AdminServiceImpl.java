package com.moyun.service.impl;

import com.moyun.entity.Book;
import com.moyun.entity.Comment;
import com.moyun.entity.ReadingNote;
import com.moyun.entity.User;
import com.moyun.mapper.BookMapper;
import com.moyun.mapper.CommentMapper;
import com.moyun.mapper.ReadingNoteMapper;
import com.moyun.mapper.UserMapper;
import com.moyun.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private ReadingNoteMapper readingNoteMapper;

    @Autowired
    private CommentMapper commentMapper;

    private void checkAdmin(Integer adminId) {
        User admin = userMapper.findById(adminId);
        if (admin == null || !"ROLE_ADMIN".equals(admin.getRole())) {
            throw new RuntimeException("没有管理员权限");
        }
    }

    @Override
    public List<User> getAllUsers(Integer adminId) {
        checkAdmin(adminId);
        return userMapper.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(Integer adminId, Integer targetUserId) {
        checkAdmin(adminId);
        
        // 检查目标用户是否存在
        User targetUser = userMapper.findById(targetUserId);
        if (targetUser == null) {
            throw new RuntimeException("用户不存在");
        }

        // 不能删除管理员
        if ("ROLE_ADMIN".equals(targetUser.getRole())) {
            throw new RuntimeException("不能删除管理员用户");
        }

        // 删除用户相关的所有数据
        commentMapper.deleteByUserId(targetUserId);
        readingNoteMapper.deleteByUserId(targetUserId);
        bookMapper.deleteByUserId(targetUserId);
        userMapper.deleteById(targetUserId);
    }

    @Override
    public List<Book> getAllBooks(Integer adminId) {
        checkAdmin(adminId);
        return bookMapper.findAll();
    }

    @Override
    @Transactional
    public void deleteBook(Integer adminId, Integer bookId) {
        checkAdmin(adminId);
        
        // 检查书籍是否存在
        Book book = bookMapper.findById(bookId);
        if (book == null) {
            throw new RuntimeException("书籍不存在");
        }

        // 删除书籍相关的所有数据
        commentMapper.deleteByBookId(bookId);
        readingNoteMapper.deleteByBookId(bookId);
        bookMapper.deleteById(bookId);
    }

    @Override
    public List<ReadingNote> getAllNotes(Integer adminId) {
        checkAdmin(adminId);
        return readingNoteMapper.findAll();
    }

    @Override
    @Transactional
    public void deleteNote(Integer adminId, Integer noteId) {
        checkAdmin(adminId);
        
        // 检查笔记是否存在
        ReadingNote note = readingNoteMapper.findById(noteId);
        if (note == null) {
            throw new RuntimeException("笔记不存在");
        }

        // 删除笔记相关的所有数据
        commentMapper.deleteByNoteId(noteId);
        readingNoteMapper.deleteById(noteId);
    }

    @Override
    public List<Comment> getAllComments(Integer adminId) {
        checkAdmin(adminId);
        return commentMapper.findAll();
    }

    @Override
    @Transactional
    public void deleteComment(Integer adminId, Integer commentId) {
        checkAdmin(adminId);
        
        // 检查评论是否存在
        Comment comment = commentMapper.findById(commentId);
        if (comment == null) {
            throw new RuntimeException("评论不存在");
        }

        commentMapper.deleteById(commentId);
    }
} 