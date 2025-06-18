package com.moyun.mapper;

import com.moyun.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BlogMapper {
    
    /**
     * 插入博客
     */
    int insert(Blog blog);
    
    /**
     * 根据ID查询博客
     */
    Blog selectById(@Param("id") Integer id);
    
    /**
     * 根据用户ID查询博客
     */
    List<Blog> selectByUserId(@Param("userId") Integer userId);
    
    /**
     * 查询所有公开的博客
     */
    List<Blog> selectAllPublic();
    
    /**
     * 更新博客
     */
    int updateById(Blog blog);
    
    /**
     * 删除博客
     */
    int deleteById(@Param("id") Integer id);
    
    /**
     * 增加浏览量
     */
    int incrementViewCount(@Param("id") Integer id);
    
    /**
     * 增加点赞数
     */
    int incrementLikeCount(@Param("id") Integer id);
    
    /**
     * 减少点赞数
     */
    int decrementLikeCount(@Param("id") Integer id);
} 