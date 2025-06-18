package com.moyun.dto;

public class CommentDTO {
    private Integer targetId;
    private String type;
    private String content;

    public Integer getTargetId() { return targetId; }
    public void setTargetId(Integer targetId) { this.targetId = targetId; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
} 