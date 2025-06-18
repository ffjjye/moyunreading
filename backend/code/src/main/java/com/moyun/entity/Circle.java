package com.moyun.entity;

import java.time.LocalDateTime;

public class Circle {
    private Integer id;
    private String name;
    private String description;
    private Integer creatorId;
    private Integer mentorId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Integer getCreatorId() { return creatorId; }
    public void setCreatorId(Integer creatorId) { this.creatorId = creatorId; }
    
    public Integer getMentorId() { return mentorId; }
    public void setMentorId(Integer mentorId) { this.mentorId = mentorId; }
    
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    
    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
} 