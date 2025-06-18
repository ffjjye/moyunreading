package com.moyun.controller;

import com.moyun.common.Result;
import com.moyun.dto.UserDTO;
import com.moyun.entity.User;
import com.moyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/Register")
    public Result<User> register(@RequestBody UserDTO userDTO) {
        try {
            User user = userService.register(userDTO);
            return Result.success("注册成功", user);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/Login")
    public Result<User> login(@RequestBody UserDTO userDTO, HttpSession session) {
        try {
            User user = userService.login(userDTO.getUsername(), userDTO.getPassword());
            // 将用户信息存入session
            session.setAttribute("userId", user.getId());
            return Result.success("登录成功", user);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/UserInfo")
    public Result<User> getUserInfo(HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            User user = userService.getUserInfo(userId);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/Logout")
    public Result<Void> logout(HttpSession session) {
        session.invalidate();
        return Result.success("退出成功", null);
    }
} 