package com.moyun.mapper;

import com.moyun.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Integer id);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO user (username, password, email, role, mentor_id, avatar, introduction, create_time, update_time) " +
            "VALUES (#{username}, #{password}, #{email}, #{role}, #{mentorId}, #{avatar}, #{introduction}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Update("UPDATE user SET password = #{password}, email = #{email}, role = #{role}, " +
            "mentor_id = #{mentorId}, avatar = #{avatar}, introduction = #{introduction}, " +
            "update_time = #{updateTime} WHERE id = #{id}")
    void update(User user);

    @Select("SELECT u.* FROM user u " +
            "JOIN circle_member cm ON u.id = cm.user_id " +
            "WHERE cm.circle_id = #{circleId}")
    List<User> findByCircleId(Integer circleId);

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteById(Integer id);

    @Select("SELECT * FROM user WHERE mentor_id = #{mentorId}")
    List<User> findByMentorId(Integer mentorId);
} 