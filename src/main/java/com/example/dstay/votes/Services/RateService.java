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

import java.util.Objects;
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

    public ResponseEntity<Integer> execVoteNews(RateDTO rateDTO) {
        try {
            JwtUtils jwtUtils = new JwtUtils();
            String username = jwtUtils.extractUsername(rateDTO.getToken());
            User user = userRepository.findByUsernameOrEmail(username, username);
            Optional<News> newsOpt = newsRepository.findById(rateDTO.getNews_id());

            if (newsOpt.isPresent()) {
                News news = newsOpt.get();
                Optional<Vote> voteOpt = voteRepository.findByUsersIdAndNewsId(user.getId(), news.getId());

                if (voteOpt.isPresent()) {
                    Vote vote = voteOpt.get();
                    if (Objects.equals(vote.getNews().getId(), rateDTO.getNews_id())) {
                        voteRepository.delete(vote);
                        news.setVotes(news.getVotes() - 1);
                        newsRepository.save(news);
                        return ResponseEntity.ok(0);
                    }
                } else {
                    Vote vote1 = new Vote();
                    vote1.setUsers(user);
                    vote1.setNews(news);
                    news.setVotes(news.getVotes() + 1);
                    newsRepository.save(news);
                    voteRepository.save(vote1);
                    return ResponseEntity.ok(1);
                }
            }

            return ResponseEntity.ok(0);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Integer> execVoteComment(RateDTO rateDTO) {
        try {
            JwtUtils jwtUtils = new JwtUtils();
            String username = jwtUtils.extractUsername(rateDTO.getToken());
            User user = userRepository.findByUsernameOrEmail(username, username);

            Optional<Comment> commentOpt = commentRepository.findById(rateDTO.getComment_id());
            if (!commentOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            Comment comment = commentOpt.get();
            Optional<Vote> voteOpt = voteRepository.findByUsersIdAndCommentId(user.getId(), rateDTO.getComment_id());

            if (voteOpt.isPresent()) {
                voteRepository.delete(voteOpt.get());
                comment.setVotes(comment.getVotes() - 1);
                commentRepository.save(comment);
                return ResponseEntity.ok(0);
            } else {
                Vote newVote = new Vote();
                newVote.setUsers(user);
                newVote.setComment(comment);
                voteRepository.save(newVote);
                comment.setVotes(comment.getVotes() + 1);
                commentRepository.save(comment);
                return ResponseEntity.ok(1);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
