package com.moyun.controller;

import com.moyun.common.Result;
import com.moyun.entity.Message;
import com.moyun.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {
    
    @Autowired
    private MessageService messageService;
    
    /**
     * 发送消息
     */
    @PostMapping
    public Result<Message> sendMessage(@RequestBody Message message) {
        try {
            Message sentMessage = messageService.sendMessage(message);
            return Result.success(sentMessage);
        } catch (Exception e) {
            return Result.error("发送消息失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取用户消息列表
     */
    @GetMapping("/user/{userId}")
    public Result<List<Message>> getUserMessages(@PathVariable Integer userId) {
        try {
            List<Message> messages = messageService.getUserMessages(userId);
            return Result.success(messages);
        } catch (Exception e) {
            return Result.error("获取消息失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取对话记录
     */
    @GetMapping("/conversation")
    public Result<List<Message>> getConversation(@RequestParam Integer userId1, @RequestParam Integer userId2) {
        try {
            List<Message> messages = messageService.getConversation(userId1, userId2);
            return Result.success(messages);
        } catch (Exception e) {
            return Result.error("获取对话失败: " + e.getMessage());
        }
    }
    
    /**
     * 标记消息为已读
     */
    @PutMapping("/{messageId}/read")
    public Result<Void> markAsRead(@PathVariable Integer messageId) {
        try {
            messageService.markAsRead(messageId);
            return Result.success();
        } catch (Exception e) {
            return Result.error("标记已读失败: " + e.getMessage());
        }
    }
    
    /**
     * 获取未读消息数量
     */
    @GetMapping("/unread/count/{userId}")
    public Result<Integer> getUnreadCount(@PathVariable Integer userId) {
        try {
            Integer count = messageService.getUnreadCount(userId);
            return Result.success(count);
        } catch (Exception e) {
            return Result.error("获取未读数量失败: " + e.getMessage());
        }
    }
    
    /**
     * 删除消息
     */
    @DeleteMapping("/{messageId}")
    public Result<Void> deleteMessage(@PathVariable Integer messageId) {
        try {
            messageService.deleteMessage(messageId);
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除消息失败: " + e.getMessage());
        }
    }
} 