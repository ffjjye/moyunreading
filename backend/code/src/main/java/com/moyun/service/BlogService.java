package com.moyun.service;

import com.moyun.entity.Blog;
import java.util.List;

public interface BlogService {
    
    /**
     * 创建博客
     */
    Blog createBlog(Blog blog);
    
    /**
     * 更新博客
     */
    Blog updateBlog(Blog blog);
    
    /**
     * 获取博客详情
     */
    Blog getBlogById(Integer blogId);
    
    /**
     * 获取用户的博客列表
     */
    List<Blog> getUserBlogs(Integer userId);
    
    /**
     * 获取所有公开的博客
     */
    List<Blog> getAllPublicBlogs();
    
    /**
     * 删除博客
     */
    void deleteBlog(Integer blogId);
    
    /**
     * 增加博客浏览量
     */
    void incrementViewCount(Integer blogId);
    
    /**
     * 点赞博客
     */
    void likeBlog(Integer blogId, Integer userId);
    
    /**
     * 取消点赞
     */
    void unlikeBlog(Integer blogId, Integer userId);
} 