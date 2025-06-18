package com.moyun.service;

import com.moyun.entity.Notification;
import java.util.List;

public interface NotificationService {
    
    /**
     * 创建通知
     */
    Notification createNotification(Notification notification);
    
    /**
     * 获取用户的通知列表
     */
    List<Notification> getUserNotifications(Integer userId);
    
    /**
     * 获取用户未读通知
     */
    List<Notification> getUnreadNotifications(Integer userId);
    
    /**
     * 标记通知为已读
     */
    void markAsRead(Integer notificationId);
    
    /**
     * 标记所有通知为已读
     */
    void markAllAsRead(Integer userId);
    
    /**
     * 删除通知
     */
    void deleteNotification(Integer notificationId);
    
    /**
     * 获取未读通知数量
     */
    Integer getUnreadCount(Integer userId);
    
    /**
     * 发送系统通知
     */
    void sendSystemNotification(Integer userId, String title, String content);
    
    /**
     * 发送导师通知
     */
    void sendMentorNotification(Integer mentorId, Integer studentId, String title, String content);
} 