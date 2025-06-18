package com.moyun.service.impl;

import com.moyun.entity.Message;
import com.moyun.service.MessageService;
import com.moyun.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    
    @Autowired
    private MessageMapper messageMapper;
    
    @Override
    public Message sendMessage(Message message) {
        message.setCreateTime(LocalDateTime.now());
        message.setIsRead(false);
        messageMapper.insert(message);
        return message;
    }
    
    @Override
    public List<Message> getUserMessages(Integer userId) {
        return messageMapper.selectByUserId(userId);
    }
    
    @Override
    public List<Message> getConversation(Integer userId1, Integer userId2) {
        return messageMapper.selectConversation(userId1, userId2);
    }
    
    @Override
    public void markAsRead(Integer messageId) {
        Message message = new Message();
        message.setId(messageId);
        message.setIsRead(true);
        message.setReadTime(LocalDateTime.now());
        messageMapper.updateById(message);
    }
    
    @Override
    public Integer getUnreadCount(Integer userId) {
        return messageMapper.countUnreadByUserId(userId);
    }
    
    @Override
    public void deleteMessage(Integer messageId) {
        messageMapper.deleteById(messageId);
    }
} 