package org.proleesh.controller;

import lombok.RequiredArgsConstructor;
import org.proleesh.entity.Comment;
import org.proleesh.entity.User;
import org.proleesh.repository.CommentRepository;
import org.proleesh.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/profile")
    public User getUserProfile(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping("/{userId}/comments")
    public List<Comment> getCommentsForUser(@PathVariable Long userId){
        return commentRepository.findByCommentedUserId(userId);
    }

    @PostMapping("/{userId}/comments")
    public String leaveComment(@AuthenticationPrincipal User user,
                               @PathVariable Long userId,
                               @RequestParam String content){
        User commentedUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자 찾을 수 없습니다."));
        Comment comment = new Comment(user, commentedUser, content);
        commentRepository.save(comment);
        return "댓글 추가 성공";
    }
}
