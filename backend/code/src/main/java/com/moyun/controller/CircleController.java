package com.moyun.controller;

import com.moyun.common.Result;
import com.moyun.dto.CircleDTO;
import com.moyun.entity.Circle;
import com.moyun.entity.User;
import com.moyun.service.CircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/circle")
@CrossOrigin
public class CircleController {

    @Autowired
    private CircleService circleService;

    @PostMapping
    public Result<Circle> createCircle(@RequestBody CircleDTO circleDTO, HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            Circle circle = circleService.createCircle(circleDTO, userId);
            return Result.success("创建圈子成功", circle);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/mentor/{mentorId}")
    public Result<Circle> getMentorCircle(@PathVariable Integer mentorId) {
        try {
            Circle circle = circleService.getMentorCircle(mentorId);
            return Result.success(circle);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/{circleId}/members")
    public Result<List<User>> getCircleMembers(@PathVariable Integer circleId) {
        try {
            List<User> members = circleService.getCircleMembers(circleId);
            return Result.success(members);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/{circleId}/join")
    public Result<Void> joinCircle(@PathVariable Integer circleId, HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            circleService.joinCircle(circleId, userId);
            return Result.success("加入圈子成功", null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/{circleId}/leave")
    public Result<Void> leaveCircle(@PathVariable Integer circleId, HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            circleService.leaveCircle(circleId, userId);
            return Result.success("退出圈子成功", null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
} 