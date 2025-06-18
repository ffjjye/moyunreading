package com.moyun.mapper;

import com.moyun.entity.Circle;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CircleMapper {
    @Insert("INSERT INTO circle (name, description, creator_id, mentor_id, create_time, update_time) " +
            "VALUES (#{name}, #{description}, #{creatorId}, #{mentorId}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Circle circle);

    @Select("SELECT * FROM circle WHERE mentor_id = #{mentorId}")
    Circle findByMentorId(Integer mentorId);

    @Select("SELECT EXISTS(SELECT 1 FROM circle_member WHERE circle_id = #{circleId} AND user_id = #{userId})")
    boolean isMember(@Param("circleId") Integer circleId, @Param("userId") Integer userId);

    @Insert("INSERT INTO circle_member (circle_id, user_id) VALUES (#{circleId}, #{userId})")
    void addMember(@Param("circleId") Integer circleId, @Param("userId") Integer userId);

    @Delete("DELETE FROM circle_member WHERE circle_id = #{circleId} AND user_id = #{userId}")
    void removeMember(@Param("circleId") Integer circleId, @Param("userId") Integer userId);
} 