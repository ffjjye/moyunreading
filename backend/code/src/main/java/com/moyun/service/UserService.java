package com.moyun.service;

import com.moyun.dto.UserDTO;
import com.moyun.entity.User;

public interface UserService {
    User register(UserDTO userDTO) throws Exception;
    User login(String username, String password) throws Exception;
    User getUserInfo(Integer userId) throws Exception;
} 