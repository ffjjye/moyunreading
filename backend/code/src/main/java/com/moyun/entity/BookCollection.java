package com.moyun.entity;

import java.time.LocalDateTime;

public class BookCollection {
    private Integer id;
    private Integer bookId;
    private Integer userId;
    private LocalDateTime collectTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public LocalDateTime getCollectTime() { return collectTime; }
    public void setCollectTime(LocalDateTime collectTime) { this.collectTime = collectTime; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
} 