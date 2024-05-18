package com.example.dstay.main.Services;

import com.example.dstay.categories.Entity.Category;
import com.example.dstay.categories.Repository.CategoryRepository;
import com.example.dstay.comments.Entity.Comment;
import com.example.dstay.comments.Repository.CommentRepository;
import com.example.dstay.main.DTO.*;
import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;
import com.example.dstay.news.Entity.News;
import com.example.dstay.news.Repository.NewsActivityRepository;
import com.example.dstay.news.Repository.NewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final NewsRepository newsRepository;
    private final CommentRepository commentRepository;
    private final NewsActivityRepository newsActivityRepository;
    private final CategoryRepository categoryRepository;
    private final JwtUtils jwtUtils;

    public UserService(UserRepository userRepository, NewsRepository newsRepository, CommentRepository commentRepository, NewsActivityRepository newsActivityRepository, CategoryRepository categoryRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
        this.commentRepository = commentRepository;
        this.newsActivityRepository = newsActivityRepository;
        this.categoryRepository = categoryRepository;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<ServiceSelfProfileDTO> execGetSelfProfile(SelfProfileDTO token) {
        try {
            String email = jwtUtils.extractUsername(token.getToken());
            ServiceSelfProfileDTO serviceSelfProfileDTO = new ServiceSelfProfileDTO();
            User user = userRepository.findByUsernameOrEmail(email, email);
            serviceSelfProfileDTO.setBio(user.getBio());
            serviceSelfProfileDTO.setSpecialization(user.getSpecialization());
            serviceSelfProfileDTO.setName(user.getName());
            serviceSelfProfileDTO.setSurname(user.getSurname());
            serviceSelfProfileDTO.setUsername(user.getUsername());
            return ResponseEntity.ok(serviceSelfProfileDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    public ResponseEntity<HttpStatus> execUpdateProfile(UpdateServiceSelfProfileDTO updateServiceSelfProfileDTO) {
        try {
            String email = jwtUtils.extractUsername(updateServiceSelfProfileDTO.getToken());
            User user = userRepository.findByUsernameOrEmail(email, email);
            if (user.getBio() == null || !user.getBio().equals(updateServiceSelfProfileDTO.getBio())) {
                user.setBio(updateServiceSelfProfileDTO.getBio());
            }
            if (user.getName() == null || !user.getName().equals(updateServiceSelfProfileDTO.getName())) {
                user.setName(updateServiceSelfProfileDTO.getName());
            }
            if (user.getSurname() == null || !user.getSurname().equals(updateServiceSelfProfileDTO.getSurname())) {
                user.setSurname(updateServiceSelfProfileDTO.getSurname());
            }
            if (user.getSpecialization() == null || !user.getSpecialization().equals(updateServiceSelfProfileDTO.getSpecialization())) {
                user.setSpecialization(updateServiceSelfProfileDTO.getSpecialization());
            }
            userRepository.save(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<UserActivityNewsDTO> execUserNewsActivity(SelfProfileDTO tokenDTO, Pageable pageable){
        try{
            String email = jwtUtils.extractUsername(tokenDTO.getToken());
            User user =  userRepository.findByUsernameOrEmail(email,email);
            UserActivityNewsDTO userActivityDTO = new UserActivityNewsDTO();
            Page<News> news = newsRepository.findAllByAuthor_IdOrderByVotes(user.getId(), pageable);
            Page<SelfProfileNewsActivityDTO> newsPage = new PageImpl<>(news.stream()
                    .map(n -> {
                        SelfProfileNewsActivityDTO dto = new SelfProfileNewsActivityDTO();
                        dto.setId(n.getId());
                        dto.setTitle(n.getTitle());
                        dto.setCreationDate(n.getCreated_at());
                        dto.setVotes(n.getVotes());
                        return dto;
                    })
                    .collect(Collectors.toList()), pageable, news.getTotalElements());
            userActivityDTO.setNews(newsPage);
            return ResponseEntity.ok(userActivityDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<UserActivityCommentsDTO> execUserCommentsActivity(SelfProfileDTO tokenDTO, Pageable pageable){
        try{
            String email = jwtUtils.extractUsername(tokenDTO.getToken());
            User user =  userRepository.findByUsernameOrEmail(email,email);
            UserActivityCommentsDTO userActivityCommentsDTO = new UserActivityCommentsDTO();
            Page<Comment> comments = commentRepository.findAllByAuthor_IdOrderByCreationDateDesc(user.getId(), pageable);
            Page<SelfProfileCommentsActivityDTO> commentsPage = new PageImpl<>(comments.stream()
                    .map(n -> {
                        SelfProfileCommentsActivityDTO dto = new SelfProfileCommentsActivityDTO();
                        dto.setId(n.getId());
                        dto.setContent(n.getContent());
                        dto.setCreationDate(n.getCreationDate());
                        dto.setVotes(n.getVotes());
                        return dto;
                    })
                    .collect(Collectors.toList()), pageable, comments.getTotalElements());
            userActivityCommentsDTO.setComments(commentsPage);
            return ResponseEntity.ok(userActivityCommentsDTO);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<HttpStatus> execUserNewsActivityDelete(DeleteActivityDTO deleteActivityDTO){
        try {
            String email = jwtUtils.extractUsername(deleteActivityDTO.getToken());
            User user =  userRepository.findByUsernameOrEmail(email,email);
            Optional<News> news = newsRepository.findById(deleteActivityDTO.getId());
            if (news.isPresent()){
                if (Objects.equals(news.get().getAuthor().getId(), user.getId())){
                    newsRepository.delete(news.get());
                    return ResponseEntity.ok().build();
                }
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<HttpStatus> execUserCommentsActivityDelete(DeleteActivityDTO deleteActivityDTO){
        try {
            String email = jwtUtils.extractUsername(deleteActivityDTO.getToken());
            User user =  userRepository.findByUsernameOrEmail(email,email);
            Optional<Comment> comment = commentRepository.findById(deleteActivityDTO.getId());
            if (comment.isPresent()){
                if (Objects.equals(comment.get().getId(), user.getId())){
                    commentRepository.deleteById(comment.get().getId());
                    return ResponseEntity.ok().build();
                }
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<HttpStatus> execUserNewsActivityUpdate(UpdateNewsDTO newsDTO){
        try{
            String email = jwtUtils.extractUsername(newsDTO.getAuthor());
            User user =  userRepository.findByUsernameOrEmail(email,email);
            Optional<News> news = newsRepository.findById(newsDTO.getId());
            if(news.isPresent()){
                if (Objects.equals(user.getId(), news.get().getAuthor().getId())){
                    if (news.get().getDescription() == null || !news.get().getDescription().equals(newsDTO.getDescription())){
                        news.get().setDescription(newsDTO.getDescription());
                    }
                    if (news.get().getTitle() == null || !news.get().getTitle().equals(newsDTO.getTitle())){
                        news.get().setTitle(newsDTO.getTitle());
                    }
                    if (news.get().getURL() == null || !news.get().getURL().equals(newsDTO.getURL())){
                        news.get().setURL(newsDTO.getURL());
                    }
                    if (news.get().getCategory() == null || !news.get().getCategory().getId().equals(newsDTO.getCategories())){
                        Category category = categoryRepository.findById(newsDTO.getCategories()).get();
                        news.get().setCategory(category);
                    }
                    newsRepository.save(news.get());
                    return ResponseEntity.ok().build();
                }
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
