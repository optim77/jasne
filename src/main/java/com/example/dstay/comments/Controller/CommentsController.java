package com.example.dstay.comments.Controller;

import com.example.dstay.comments.DTO.CommentDTO;
import com.example.dstay.comments.DTO.CommentToNewsDTO;
import com.example.dstay.comments.Entity.Comment;
import com.example.dstay.comments.Repository.CommentRepository;
import com.example.dstay.comments.Services.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class CommentsController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    public CommentsController(CommentRepository commentRepository, CommentService commentService) {
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }

    @PostMapping("/add_comment")
    public ResponseEntity<String> uploadComment(@RequestBody CommentDTO comment){
        return commentService.execUploadComment(comment);
    }

    // TODO
    @GetMapping("/comment/{comment_id}")
    public Page<Comment> getCommentById(@PathVariable Long comment_id, Pageable pageable){
        return commentRepository.findAllByNews(comment_id, pageable);
    }

    @PatchMapping("/comment/{comment_id}")
    public Comment updateComment(@PathVariable Long comment_id, @RequestBody Comment comment){
        return commentService.execUpdateComment(comment_id, comment);
    }

    @DeleteMapping("/comment/{comment_id}")
    public ResponseEntity<HttpStatus> deleteCommentById(@PathVariable Long comment_id) throws ChangeSetPersister.NotFoundException {
        return commentService.execDeleteCommentById(comment_id);
    }

    @GetMapping("/news_comments/{news_id}")
    public Page<CommentToNewsDTO> getCommentsToNews(@PathVariable Long news_id, Pageable pageable){
        List<CommentToNewsDTO> commentToNewsDTOS = commentService.execGetCommentsToNews(news_id, pageable);
        long totalComments = commentRepository.countByNews_Id(news_id);
        return new PageImpl<>(commentToNewsDTOS, pageable, totalComments);
    }

}
