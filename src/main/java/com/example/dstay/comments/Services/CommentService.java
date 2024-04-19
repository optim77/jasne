package com.example.dstay.comments.Services;

import com.example.dstay.comments.Entity.Comment;
import com.example.dstay.comments.Repository.CommentRepository;
import com.example.dstay.main.Security.JwtUtils;
import com.example.dstay.news.Entity.News;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment execUploadComment(Comment sent_comment){
        Comment comment = new Comment();
        comment.setCreationDate(new Date());
        comment.setContent(sent_comment.getContent());
        comment.setNews(sent_comment.getNews());
        comment.setAuthor(sent_comment.getAuthor());
        return commentRepository.save(comment);
    }

    public ResponseEntity<HttpStatus> execDeleteCommentById(Long id) throws ChangeSetPersister.NotFoundException {
        Comment comment = commentRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        JwtUtils jwtUtils = new JwtUtils();
        HttpServletRequest request = null;
        String authHeader = request.getHeader("Authorization");
        if (jwtUtils.extractUsername(authHeader) == comment.getAuthor().getEmail()) {
            commentRepository.deleteById(id);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public Comment execUpdateComment(Long comment_id, Comment comment){
        Comment comment_to_update = commentRepository.findById(comment_id).orElseThrow();
        if (comment.getContent() != null){
            comment_to_update.setContent(comment.getContent());
        }
        return comment_to_update;
    }
}
