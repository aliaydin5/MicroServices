package com.depart.depart6.Controller;

import com.depart.depart6.Entity.Comment;
import com.depart.depart6.Service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment addComment(
            @RequestParam Long userId,
            @RequestParam Long storyId,
            @RequestParam String comment) {
        return commentService.addComment(userId, storyId, comment);
    }

    @GetMapping("/{postId}")
    public List<Comment> getCommentsForPost(@PathVariable Long postId) {
        return commentService.getCommentsForPost(postId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
