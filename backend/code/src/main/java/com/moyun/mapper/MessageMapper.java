package com.moyun.mapper;

import com.moyun.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MessageMapper {
    
    /**
     * 插入消息
     */
    int insert(Message message);
    
    /**
     * 根据用户ID查询消息
     */
    List<Message> selectByUserId(@Param("userId") Integer userId);
    
    /**
     * 查询两个用户之间的对话
     */
    List<Message> selectConversation(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);
    
    /**
     * 更新消息
     */
    int updateById(Message message);
    
    /**
     * 统计用户未读消息数量
     */
    int countUnreadByUserId(@Param("userId") Integer userId);
    
    /**
     * 删除消息
     */
    int deleteById(@Param("id") Integer id);
} 