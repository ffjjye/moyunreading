package com.moyun.entity;

import java.time.LocalDateTime;

public class Comment {
    private Integer id;
    private Integer userId;
    private Integer targetId;
    private String type;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    
    public Integer getTargetId() { return targetId; }
    public void setTargetId(Integer targetId) { this.targetId = targetId; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    
    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
} 