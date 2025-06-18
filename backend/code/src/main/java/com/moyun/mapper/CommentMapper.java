package com.moyun.mapper;

import com.moyun.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("INSERT INTO comment (user_id, target_id, type, content, create_time, update_time) " +
            "VALUES (#{userId}, #{targetId}, #{type}, #{content}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Comment comment);

    @Select("SELECT * FROM comment WHERE id = #{id}")
    Comment findById(Integer id);

    @Select("SELECT * FROM comment WHERE target_id = #{targetId} ORDER BY create_time DESC")
    List<Comment> findByTargetId(Integer targetId);

    @Select("SELECT * FROM comment WHERE user_id = #{userId} ORDER BY create_time DESC")
    List<Comment> findByUserId(Integer userId);

    @Select("SELECT * FROM comment")
    List<Comment> findAll();

    @Delete("DELETE FROM comment WHERE id = #{id}")
    void deleteById(Integer id);

    @Delete("DELETE FROM comment WHERE user_id = #{userId}")
    void deleteByUserId(Integer userId);

    @Delete("DELETE FROM comment WHERE target_id = #{noteId} AND type = 'NOTE_COMMENT'")
    void deleteByNoteId(Integer noteId);

    @Delete("DELETE FROM comment WHERE target_id = #{bookId} AND type = 'BOOK_COMMENT'")
    void deleteByBookId(Integer bookId);
} 