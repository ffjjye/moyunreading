package com.moyun.mapper;

import com.moyun.entity.ReadingNote;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReadingNoteMapper {
    @Select("SELECT * FROM reading_note WHERE id = #{id}")
    ReadingNote findById(Integer id);

    @Insert("INSERT INTO reading_note (user_id, book_id, content, likes, create_time, update_time) " +
            "VALUES (#{userId}, #{bookId}, #{content}, #{likes}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(ReadingNote note);

    @Update("UPDATE reading_note SET content = #{content}, likes = #{likes}, update_time = #{updateTime} " +
            "WHERE id = #{id}")
    void update(ReadingNote note);

    @Select("SELECT * FROM reading_note")
    List<ReadingNote> findAll();

    @Delete("DELETE FROM reading_note WHERE id = #{id}")
    void deleteById(Integer id);

    @Delete("DELETE FROM reading_note WHERE user_id = #{userId}")
    void deleteByUserId(Integer userId);

    @Delete("DELETE FROM reading_note WHERE book_id = #{bookId}")
    void deleteByBookId(Integer bookId);

    @Select("SELECT * FROM reading_note WHERE book_id = #{bookId}")
    List<ReadingNote> findByBookId(Integer bookId);

    @Select("SELECT * FROM reading_note WHERE user_id = #{userId}")
    List<ReadingNote> findByUserId(Integer userId);

    @Select("SELECT * FROM reading_note WHERE user_id IN (#{userIds})")
    List<ReadingNote> findByUserIds(@Param("userIds") List<Integer> userIds);

    @Select("SELECT COUNT(*) > 0 FROM reading_note_likes WHERE note_id = #{noteId} AND user_id = #{userId}")
    boolean hasLiked(@Param("noteId") Integer noteId, @Param("userId") Integer userId);

    @Insert("INSERT INTO reading_note_likes (note_id, user_id) VALUES (#{noteId}, #{userId})")
    void addLike(@Param("noteId") Integer noteId, @Param("userId") Integer userId);
} 