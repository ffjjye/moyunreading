package com.moyun.mapper;

import com.moyun.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("SELECT * FROM book WHERE id = #{id}")
    Book findById(Integer id);

    @Insert("INSERT INTO book (title, description, file_path, uploader_id, upload_time, create_time, update_time) " +
            "VALUES (#{title}, #{description}, #{filePath}, #{uploaderId}, #{uploadTime}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Book book);

    @Update("UPDATE book SET title = #{title}, description = #{description}, file_path = #{filePath}, " +
            "uploader_id = #{uploaderId}, upload_time = #{uploadTime}, update_time = #{updateTime} " +
            "WHERE id = #{id}")
    void update(Book book);

    @Select("SELECT * FROM book")
    List<Book> findAll();

    @Delete("DELETE FROM book WHERE id = #{id}")
    void deleteById(Integer id);

    @Delete("DELETE FROM book WHERE uploader_id = #{userId}")
    void deleteByUserId(Integer userId);
} 