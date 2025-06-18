package com.moyun.service;

import com.moyun.entity.User;
import com.moyun.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.moyun.mapper.UserMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    private UserMapper userMapper;
    
    @InjectMocks
    private UserServiceImpl userService;
    
    @Test
    public void testCreateUser() {
        // Given
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("test@example.com");
        
        when(userMapper.insert(any(User.class))).thenReturn(1);
        
        // When
        User result = userService.createUser(user);
        
        // Then
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(userMapper, times(1)).insert(any(User.class));
    }
    
    @Test
    public void testGetUserById() {
        // Given
        Integer userId = 1;
        User expectedUser = new User();
        expectedUser.setId(userId);
        expectedUser.setUsername("testuser");
        
        when(userMapper.selectById(userId)).thenReturn(expectedUser);
        
        // When
        User result = userService.getUserById(userId);
        
        // Then
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("testuser", result.getUsername());
        verify(userMapper, times(1)).selectById(userId);
    }
    
    @Test
    public void testUpdateUser() {
        // Given
        User user = new User();
        user.setId(1);
        user.setUsername("updateduser");
        
        when(userMapper.updateById(any(User.class))).thenReturn(1);
        
        // When
        User result = userService.updateUser(user);
        
        // Then
        assertNotNull(result);
        assertEquals("updateduser", result.getUsername());
        verify(userMapper, times(1)).updateById(any(User.class));
    }
    
    @Test
    public void testDeleteUser() {
        // Given
        Integer userId = 1;
        when(userMapper.deleteById(userId)).thenReturn(1);
        
        // When
        userService.deleteUser(userId);
        
        // Then
        verify(userMapper, times(1)).deleteById(userId);
    }
} 