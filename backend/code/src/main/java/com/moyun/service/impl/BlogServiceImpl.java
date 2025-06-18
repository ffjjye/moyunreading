package com.moyun.service.impl;

import com.moyun.entity.Blog;
import com.moyun.service.BlogService;
import com.moyun.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    
    @Autowired
    private BlogMapper blogMapper;
    
    @Override
    public Blog createBlog(Blog blog) {
        blog.setCreateTime(LocalDateTime.now());
        blog.setUpdateTime(LocalDateTime.now());
        blog.setViewCount(0);
        blog.setLikeCount(0);
        blog.setCommentCount(0);
        if ("PUBLISHED".equals(blog.getStatus())) {
            blog.setPublishTime(LocalDateTime.now());
        }
        blogMapper.insert(blog);
        return blog;
    }
    
    @Override
    public Blog updateBlog(Blog blog) {
        blog.setUpdateTime(LocalDateTime.now());
        if ("PUBLISHED".equals(blog.getStatus()) && blog.getPublishTime() == null) {
            blog.setPublishTime(LocalDateTime.now());
        }
        blogMapper.updateById(blog);
        return blog;
    }
    
    @Override
    public Blog getBlogById(Integer blogId) {
        return blogMapper.selectById(blogId);
    }
    
    @Override
    public List<Blog> getUserBlogs(Integer userId) {
        return blogMapper.selectByUserId(userId);
    }
    
    @Override
    public List<Blog> getAllPublicBlogs() {
        return blogMapper.selectAllPublic();
    }
    
    @Override
    public void deleteBlog(Integer blogId) {
        blogMapper.deleteById(blogId);
    }
    
    @Override
    public void incrementViewCount(Integer blogId) {
        blogMapper.incrementViewCount(blogId);
    }
    
    @Override
    public void likeBlog(Integer blogId, Integer userId) {
        blogMapper.incrementLikeCount(blogId);
        // 这里可以添加用户点赞记录的逻辑
    }
    
    @Override
    public void unlikeBlog(Integer blogId, Integer userId) {
        blogMapper.decrementLikeCount(blogId);
        // 这里可以添加用户取消点赞记录的逻辑
    }
} 