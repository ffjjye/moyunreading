package com.moyun.service;

import com.moyun.entity.Message;
import java.util.List;

public interface MessageService {
    
    /**
     * 发送消息
     */
    Message sendMessage(Message message);
    
    /**
     * 获取用户的消息列表
     */
    List<Message> getUserMessages(Integer userId);
    
    /**
     * 获取两个用户之间的对话记录
     */
    List<Message> getConversation(Integer userId1, Integer userId2);
    
    /**
     * 标记消息为已读
     */
    void markAsRead(Integer messageId);
    
    /**
     * 获取用户未读消息数量
     */
    Integer getUnreadCount(Integer userId);
    
    /**
     * 删除消息
     */
    void deleteMessage(Integer messageId);
} 