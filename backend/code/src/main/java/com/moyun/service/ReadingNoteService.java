package com.moyun.service;

import com.moyun.dto.ReadingNoteDTO;
import com.moyun.entity.ReadingNote;
import java.util.List;

public interface ReadingNoteService {
    /**
     * 创建读书笔记
     * @param noteDTO 笔记信息
     * @param userId 用户ID
     * @return 创建的笔记
     */
    ReadingNote createNote(ReadingNoteDTO noteDTO, Integer userId);

    /**
     * 获取指定书籍的所有笔记
     * @param bookId 书籍ID
     * @return 笔记列表
     */
    List<ReadingNote> getBookNotes(Integer bookId);

    /**
     * 获取指定用户的所有笔记
     * @param userId 用户ID
     * @return 笔记列表
     */
    List<ReadingNote> getUserNotes(Integer userId);

    /**
     * 获取导师学生的所有笔记
     * @param mentorId 导师ID
     * @return 笔记列表
     */
    List<ReadingNote> getMentorStudentNotes(Integer mentorId);

    /**
     * 更新笔记
     * @param noteId 笔记ID
     * @param noteDTO 更新的笔记信息
     * @param userId 用户ID
     * @return 更新后的笔记
     */
    ReadingNote updateNote(Integer noteId, ReadingNoteDTO noteDTO, Integer userId);

    /**
     * 删除笔记
     * @param noteId 笔记ID
     * @param userId 用户ID
     */
    void deleteNote(Integer noteId, Integer userId);

    /**
     * 点赞笔记
     * @param noteId 笔记ID
     * @param userId 用户ID
     */
    void likeNote(Integer noteId, Integer userId);
} 