package com.example.dstay.comments.Services;

import com.example.dstay.comments.DTO.CommentDTO;
import com.example.dstay.comments.DTO.CommentToNewsDTO;
import com.example.dstay.comments.DTO.CommentWithNewsDTO;
import com.example.dstay.comments.Entity.Comment;
import com.example.dstay.comments.Repository.CommentRepository;
import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;
import com.example.dstay.news.Entity.News;
import com.example.dstay.news.Repository.NewsRepository;
import com.example.dstay.votes.Repository.VoteRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    private final JwtUtils jwtUtilsService;

    public CommentService(CommentRepository commentRepository, NewsRepository newsRepository, UserRepository userRepository, VoteRepository voteRepository, JwtUtils jwtUtilsService) {
        this.commentRepository = commentRepository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
        this.jwtUtilsService = jwtUtilsService;
    }

    public ResponseEntity<HttpStatus> execUploadComment(CommentDTO sent_comment) {
        try{
            Optional<News> news = newsRepository.findById(sent_comment.getNews_id());
            String username = jwtUtilsService.extractUsername(sent_comment.getToken());
            Optional<User> user = Optional.ofNullable(userRepository.findByUsernameOrEmail(username, username));
            if (news.isPresent() && user.isPresent()) {
                Comment comment = new Comment();
                comment.setCreationDate(new Date());
                comment.setContent(sent_comment.getContent());
                comment.setNews(news.get());
                comment.setAuthor(user.get());
                Comment saved = commentRepository.save(comment);
                return ResponseEntity.ok(HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity<HttpStatus> execUpdateComment(CommentDTO comment) {
        try {
            Optional<Comment> comment_to_update = commentRepository.findById(comment.getComment_id());
            JwtUtils jwtUtils = new JwtUtils();
            String user_action = jwtUtils.extractUsername(comment.getToken());
            if (Objects.equals(user_action, comment_to_update.get().getAuthor().getEmail())) {
                if (comment.getContent() != null) {
                    comment_to_update.get().setContent(comment.getContent());
                    commentRepository.save(comment_to_update.get());
                }
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    public List<CommentToNewsDTO> execGetCommentsToNews(Long news_id, String token, Pageable pageable) {
        Page<Comment> comments = commentRepository.findByNews_Id_OrderByCreationDateDesc(news_id, pageable);
        List<CommentToNewsDTO> commentToNewsDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            CommentToNewsDTO toSave = new CommentToNewsDTO();
            toSave.setId(comment.getId());
            toSave.setAuthor_comment_id(comment.getAuthor().getId());
            toSave.setNews_id(comment.getNews().getId());
            toSave.setComment_create_at(new Date());
            toSave.setComment_content(comment.getContent());
            toSave.setVotes(comment.getVotes());
            toSave.setAuthor_comment_name(comment.getAuthor().getUsername().toString().split("@")[0]);
            if (token != null){
                String username = jwtUtilsService.extractUsername(token);
                Long userId = userRepository.findByUsernameOrEmail(username, username).getId();
                if (voteRepository.findByUsersIdAndCommentId(userId, comment.getId()).isPresent()){
                    toSave.setUser_voted(true);
                }
            }
            commentToNewsDTOS.add(toSave);
        }
        return commentToNewsDTOS;
    }

    public CommentWithNewsDTO getCommentByIdWithNews(Long comment_id) {
        try {
            CommentWithNewsDTO commentDTO = new CommentWithNewsDTO();
            Optional<Comment> comment = commentRepository.findById(comment_id);
            Optional<News> news = newsRepository.findById(comment.get().getNews().getId());
            if (news.isPresent()) {
                commentDTO.setContent(comment.get().getContent());
                commentDTO.setCommentVotes(comment.get().getVotes());
                commentDTO.setNews_id(news.get().getId());
                commentDTO.setAuthorNews(news.get().getAuthor().getId());
                commentDTO.setNewsTitle(news.get().getTitle());
                commentDTO.setNewsVotes(news.get().getVotes());
                return commentDTO;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
