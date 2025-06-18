package com.moyun.controller;

import com.moyun.common.Result;
import com.moyun.dto.ReadingNoteDTO;
import com.moyun.entity.ReadingNote;
import com.moyun.service.ReadingNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/note")
@CrossOrigin
public class ReadingNoteController {

    @Autowired
    private ReadingNoteService readingNoteService;

    @PostMapping
    public Result<ReadingNote> createNote(@RequestBody ReadingNoteDTO noteDTO, HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            ReadingNote note = readingNoteService.createNote(noteDTO, userId);
            return Result.success("创建笔记成功", note);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/book/{bookId}")
    public Result<List<ReadingNote>> getBookNotes(@PathVariable Integer bookId) {
        try {
            List<ReadingNote> notes = readingNoteService.getBookNotes(bookId);
            return Result.success(notes);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public Result<List<ReadingNote>> getUserNotes(@PathVariable Integer userId) {
        try {
            List<ReadingNote> notes = readingNoteService.getUserNotes(userId);
            return Result.success(notes);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/mentor/{mentorId}")
    public Result<List<ReadingNote>> getMentorStudentNotes(@PathVariable Integer mentorId) {
        try {
            List<ReadingNote> notes = readingNoteService.getMentorStudentNotes(mentorId);
            return Result.success(notes);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/{noteId}")
    public Result<ReadingNote> updateNote(@PathVariable Integer noteId, 
                                        @RequestBody ReadingNoteDTO noteDTO,
                                        HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            ReadingNote note = readingNoteService.updateNote(noteId, noteDTO, userId);
            return Result.success("更新笔记成功", note);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/{noteId}")
    public Result<Void> deleteNote(@PathVariable Integer noteId, HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            readingNoteService.deleteNote(noteId, userId);
            return Result.success("删除笔记成功", null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/{noteId}/like")
    public Result<Void> likeNote(@PathVariable Integer noteId, HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
                return Result.error("未登录");
            }
            readingNoteService.likeNote(noteId, userId);
            return Result.success("点赞成功", null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
} 