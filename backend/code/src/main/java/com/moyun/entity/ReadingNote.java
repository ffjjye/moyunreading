package com.moyun.entity;

import java.time.LocalDateTime;

public class ReadingNote {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private String content;
    private Integer likes;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    
    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public Integer getLikes() { return likes; }
    public void setLikes(Integer likes) { this.likes = likes; }
    
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    
    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
} 