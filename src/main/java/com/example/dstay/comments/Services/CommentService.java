package com.example.dstay.comments.Services;

import com.example.dstay.comments.DTO.CommentDTO;
import com.example.dstay.comments.DTO.CommentToNewsDTO;
import com.example.dstay.comments.Entity.Comment;
import com.example.dstay.comments.Repository.CommentRepository;
import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;
import com.example.dstay.news.Entity.News;
import com.example.dstay.news.Repository.NewsRepository;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    private final JwtUtils jwtUtilsService;

    public CommentService(CommentRepository commentRepository, NewsRepository newsRepository, UserRepository userRepository, JwtUtils jwtUtilsService) {
        this.commentRepository = commentRepository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.jwtUtilsService = jwtUtilsService;
    }

    public ResponseEntity<String> execUploadComment(CommentDTO sent_comment){
        Optional<News> news = newsRepository.findById(sent_comment.getNews_id());
        String username = jwtUtilsService.extractUsername(sent_comment.getToken());
        Optional<User> user = Optional.ofNullable(userRepository.findByUsernameOrEmail(username, username));
        if (news.isPresent() && user.isPresent()){
            Comment comment = new Comment();
            comment.setCreationDate(new Date());
            comment.setContent(sent_comment.getContent());
            comment.setNews(news.get());
            comment.setAuthor(user.get());
            Comment saved =  commentRepository.save(comment);
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return null;

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

    public List<CommentToNewsDTO> execGetCommentsToNews(Long news_id , Pageable pageable){
        Page<Comment> comments = commentRepository.findByNews_Id_OrderByCreationDateDesc(news_id, pageable);
        List<CommentToNewsDTO> commentToNewsDTOS = new ArrayList<>();

        for(Comment comment : comments){
            CommentToNewsDTO toSave = new CommentToNewsDTO();
            toSave.setAuthor_comment_id(comment.getAuthor().getId());
            toSave.setNews_id(comment.getNews().getId());
            toSave.setComment_create_at(new Date());
            toSave.setComment_content(comment.getContent());
            toSave.setVotes(comment.getVotes());
            toSave.setAuthor_comment_name(comment.getAuthor().getUsername().toString().split("@")[0]);
            commentToNewsDTOS.add(toSave);
        }
        return commentToNewsDTOS;
    }
}
