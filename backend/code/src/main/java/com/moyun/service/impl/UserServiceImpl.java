package com.moyun.service.impl;

import com.moyun.dto.UserDTO;
import com.moyun.entity.User;
import com.moyun.mapper.UserMapper;
import com.moyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User register(UserDTO userDTO) throws Exception {
        // 检查用户名是否已存在
        User existingUser = userMapper.findByUsername(userDTO.getUsername());
        if (existingUser != null) {
            throw new Exception("用户名已存在");
        }

        // 创建新用户
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); // 实际应用中应该加密
        user.setEmail(userDTO.getEmail());

        // 保存用户
        userMapper.insert(user);
        return user;
    }

    @Override
    public User login(String username, String password) throws Exception {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new Exception("用户不存在");
        }
        if (!user.getPassword().equals(password)) { // 实际应用中应该比较加密后的密码
            throw new Exception("密码错误");
        }
        return user;
    }

    @Override
    public User getUserInfo(Integer userId) throws Exception {
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new Exception("用户不存在");
        }
        return user;
    }
} 