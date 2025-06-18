package com.moyun.entity;

import java.time.LocalDateTime;

public class Book {
    private Integer id;
    private String title;
    private String description;
    private String filePath;
    private Integer uploaderId;
    private LocalDateTime uploadTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public Integer getUploaderId() { return uploaderId; }
    public void setUploaderId(Integer uploaderId) { this.uploaderId = uploaderId; }
    public LocalDateTime getUploadTime() { return uploadTime; }
    public void setUploadTime(LocalDateTime uploadTime) { this.uploadTime = uploadTime; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
} 