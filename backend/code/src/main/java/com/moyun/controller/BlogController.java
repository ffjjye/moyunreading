package com.moyun.controller;

import com.moyun.common.Result;
import com.moyun.entity.Blog;
import com.moyun.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin(origins = "*")
public class BlogController {
    
    @Autowired
    private BlogService blogService;
    
    /**
     * 创建博客
     */
    @PostMapping
    public Result<Blog> createBlog(@RequestBody Blog blog) {
        try {
            Blog createdBlog = blogService.createBlog(blog);
            return Result.success(createdBlog);
        } catch (Exception e) {
            return Result.error("创建博客失败: " + e.getMessage());
        }
    }
    
    /**
     * 更新博客
     */
    @PutMapping("/{blogId}")
    public Result<Blog> updateBlog(@PathVariable Integer blogId, @RequestBody Blog blog) {
        try {
            blog.setId(blogId);
            Blog updatedBlog = blogService.updateBlog(blog);
            return Result.success(updatedBlog);
        } catch (Exception e) {
            return Result.error("更新博客失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取博客详情
     */
    @GetMapping("/{blogId}")
    public Result<Blog> getBlogById(@PathVariable Integer blogId) {
        try {
            Blog blog = blogService.getBlogById(blogId);
            if (blog != null) {
                blogService.incrementViewCount(blogId);
                return Result.success(blog);
            } else {
                return Result.error("博客不存在");
            }
        } catch (Exception e) {
            return Result.error("获取博客失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取用户的博客列表
     */
    @GetMapping("/user/{userId}")
    public Result<List<Blog>> getUserBlogs(@PathVariable Integer userId) {
        try {
            List<Blog> blogs = blogService.getUserBlogs(userId);
            return Result.success(blogs);
        } catch (Exception e) {
            return Result.error("获取用户博客失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取所有公开博客
     */
    @GetMapping("/public")
    public Result<List<Blog>> getAllPublicBlogs() {
        try {
            List<Blog> blogs = blogService.getAllPublicBlogs();
            return Result.success(blogs);
        } catch (Exception e) {
            return Result.error("获取公开博客失败: " + e.getMessage());
        }
    }
    
    /**
     * 删除博客
     */
    @DeleteMapping("/{blogId}")
    public Result<Void> deleteBlog(@PathVariable Integer blogId) {
        try {
            blogService.deleteBlog(blogId);
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除博客失败: " + e.getMessage());
        }
    }
    
    /**
     * 点赞博客
     */
    @PostMapping("/{blogId}/like")
    public Result<Void> likeBlog(@PathVariable Integer blogId, @RequestParam Integer userId) {
        try {
            blogService.likeBlog(blogId, userId);
            return Result.success();
        } catch (Exception e) {
            return Result.error("点赞失败: " + e.getMessage());
        }
    }
    
    /**
     * 取消点赞
     */
    @DeleteMapping("/{blogId}/like")
    public Result<Void> unlikeBlog(@PathVariable Integer blogId, @RequestParam Integer userId) {
        try {
            blogService.unlikeBlog(blogId, userId);
            return Result.success();
        } catch (Exception e) {
            return Result.error("取消点赞失败: " + e.getMessage());
        }
    }
} 