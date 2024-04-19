package com.example.dstay.news.Services;

import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;
import com.example.dstay.news.DTOs.NewsDTO;
import jakarta.servlet.http.HttpServletRequest;
import com.example.dstay.news.Entity.News;
import com.example.dstay.news.Repository.NewsRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service

public class NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public NewsService(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    public Long execUploadNews(NewsDTO newsDTO) {
        News news = new News();
        news.setAuthor(userResolver(newsDTO.getAuthor()));
        news.setCategories(newsDTO.getCategories());
        news.setURL(newsDTO.getURL());
        news.setDescription(newsDTO.getDescription());
        news.setCreated_at(new Date());
        news.setTitle(newsDTO.getTitle());
        return newsRepository.save(news).getId();


    }

    public User userResolver(String token){
        JwtUtils jwtUtils = new JwtUtils();
        String email = jwtUtils.extractUsername(token);
        User user = userRepository.findByUsernameOrEmail(email, email);
        if(jwtUtils.isTokenValid(token, user)){
            return user;
        }
        return null;
    }

    public ResponseEntity<HttpStatus> execDeleteNews(Long news_id) throws Exception {
        News news = newsRepository.findById(news_id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        JwtUtils jwtUtils = new JwtUtils();
        HttpServletRequest request = null;
        String authHeader = request.getHeader("Authorization");
        if (jwtUtils.extractUsername(authHeader) == news.getAuthor().getEmail()) {
            newsRepository.deleteById(news_id);

        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public News execUpdateNews(Long news_id, News newsToUpdate) throws ChangeSetPersister.NotFoundException {
        News news = newsRepository.findById(news_id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        if(newsToUpdate.getAuthor() != null){
            news.setAuthor(newsToUpdate.getAuthor());
        }
        if(newsToUpdate.getDescription() != null){
            news.setDescription(newsToUpdate.getDescription());
        }
        if(newsToUpdate.getURL() != null){
            news.setURL(newsToUpdate.getURL());
        }
        return newsRepository.save(newsToUpdate);
    }
}
