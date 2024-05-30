package com.example.dstay.comments.Controller;

import com.example.dstay.comments.DTO.CommentDTO;
import com.example.dstay.comments.DTO.CommentToNewsDTO;
import com.example.dstay.comments.DTO.CommentWithNewsDTO;
import com.example.dstay.comments.Entity.Comment;
import com.example.dstay.comments.Repository.CommentRepository;
import com.example.dstay.comments.Services.CommentService;
import com.example.dstay.news.Entity.News;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public CommentWithNewsDTO getCommentByIdWithNews(@PathVariable Long comment_id){
        return commentService.getCommentByIdWithNews(comment_id);

    }

    @PatchMapping("/comment")
    public ResponseEntity<HttpStatus> updateComment(@RequestBody CommentDTO comment){
        return commentService.execUpdateComment(comment);
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
