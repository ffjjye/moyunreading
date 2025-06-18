package com.moyun.service.impl;

import com.moyun.dto.ReadingNoteDTO;
import com.moyun.entity.Book;
import com.moyun.entity.ReadingNote;
import com.moyun.entity.User;
import com.moyun.exception.BusinessException;
import com.moyun.mapper.BookMapper;
import com.moyun.mapper.ReadingNoteMapper;
import com.moyun.mapper.UserMapper;
import com.moyun.service.ReadingNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadingNoteServiceImpl implements ReadingNoteService {

    @Autowired
    private ReadingNoteMapper readingNoteMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public ReadingNote createNote(ReadingNoteDTO noteDTO, Integer userId) {
        // 检查书籍是否存在
        Book book = bookMapper.findById(noteDTO.getBookId());
        if (book == null) {
            throw new BusinessException("书籍不存在");
        }

        // 创建笔记
        ReadingNote note = new ReadingNote();
        note.setBookId(noteDTO.getBookId());
        note.setUserId(userId);
        note.setContent(noteDTO.getContent());
        note.setCreateTime(LocalDateTime.now());
        note.setUpdateTime(LocalDateTime.now());
        note.setLikes(0);

        readingNoteMapper.insert(note);
        return note;
    }

    @Override
    public List<ReadingNote> getBookNotes(Integer bookId) {
        return readingNoteMapper.findByBookId(bookId);
    }

    @Override
    public List<ReadingNote> getUserNotes(Integer userId) {
        return readingNoteMapper.findByUserId(userId);
    }

    @Override
    public List<ReadingNote> getMentorStudentNotes(Integer mentorId) {
        // 获取导师的所有学生
        List<User> students = userMapper.findByMentorId(mentorId);
        if (students.isEmpty()) {
            return new ArrayList<>();
        }

        // 获取所有学生的笔记
        return readingNoteMapper.findByUserIds(students.stream()
                .map(User::getId)
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public ReadingNote updateNote(Integer noteId, ReadingNoteDTO noteDTO, Integer userId) {
        // 检查笔记是否存在
        ReadingNote note = readingNoteMapper.findById(noteId);
        if (note == null) {
            throw new BusinessException("笔记不存在");
        }

        // 检查权限
        if (!note.getUserId().equals(userId)) {
            throw new BusinessException("无权修改此笔记");
        }

        // 更新笔记
        note.setContent(noteDTO.getContent());
        note.setUpdateTime(LocalDateTime.now());

        readingNoteMapper.update(note);
        return note;
    }

    @Override
    @Transactional
    public void deleteNote(Integer noteId, Integer userId) {
        // 检查笔记是否存在
        ReadingNote note = readingNoteMapper.findById(noteId);
        if (note == null) {
            throw new BusinessException("笔记不存在");
        }

        // 检查权限
        if (!note.getUserId().equals(userId)) {
            throw new BusinessException("无权删除此笔记");
        }

        // 删除笔记
        readingNoteMapper.deleteById(noteId);
    }

    @Override
    @Transactional
    public void likeNote(Integer noteId, Integer userId) {
        // 检查笔记是否存在
        ReadingNote note = readingNoteMapper.findById(noteId);
        if (note == null) {
            throw new BusinessException("笔记不存在");
        }

        // 检查是否已经点赞
        if (readingNoteMapper.hasLiked(noteId, userId)) {
            throw new BusinessException("已经点赞过此笔记");
        }

        // 添加点赞记录
        readingNoteMapper.addLike(noteId, userId);
        
        // 更新笔记点赞数
        note.setLikes(note.getLikes() + 1);
        readingNoteMapper.update(note);
    }
} 