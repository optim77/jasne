package com.example.dstay.votes.Services;

import com.example.dstay.comments.Entity.Comment;
import com.example.dstay.comments.Repository.CommentRepository;
import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;
import com.example.dstay.news.Entity.News;
import com.example.dstay.news.Repository.NewsRepository;
import com.example.dstay.votes.DTO.RateDTO;
import com.example.dstay.votes.Entity.Vote;
import com.example.dstay.votes.Repository.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RateService {
    private final NewsRepository newsRepository;
    private final CommentRepository commentRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    public RateService(NewsRepository newsRepository, CommentRepository commentRepository, VoteRepository voteRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.commentRepository = commentRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<HttpStatus> execVoteNews(RateDTO rateDTO){
        try{
            JwtUtils jwtUtils = new JwtUtils();
            String username = jwtUtils.extractUsername(rateDTO.getToken());
            User user = userRepository.findByUsernameOrEmail(username, username);
            Optional<Vote> vote = voteRepository.findByUsers(user.getId());
            if (vote.isPresent()){
                voteRepository.delete(vote.get());
                return ResponseEntity.ok().build();
            }else {
                Vote vote1 = new Vote();
                vote1.setUsers(user);
                Optional<News> news = newsRepository.findById(rateDTO.getNews_id());
                news.ifPresent(vote1::setNews);
                return ResponseEntity.ok().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<HttpStatus> execVoteComment(RateDTO rateDTO){
        try{
            JwtUtils jwtUtils = new JwtUtils();
            String username = jwtUtils.extractUsername(rateDTO.getToken());
            User user = userRepository.findByUsernameOrEmail(username, username);
            Optional<Vote> vote = voteRepository.findByUsers(user.getId());
            if (vote.isPresent()){
                voteRepository.delete(vote.get());
                return ResponseEntity.ok().build();
            }else {
                Vote vote1 = new Vote();
                vote1.setUsers(user);
                Optional<Comment> comment = commentRepository.findById(rateDTO.getComment_id());
                comment.ifPresent(vote1::setComment);
                return ResponseEntity.ok().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
