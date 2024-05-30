package com.example.dstay.news.Services;

import com.example.dstay.categories.Entity.Category;
import com.example.dstay.categories.Repository.CategoryRepository;
import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;
import com.example.dstay.news.DTOs.CategoryNewsDTO;
import com.example.dstay.news.DTOs.NewsDTO;
import com.example.dstay.news.DTOs.NewsWithAuthorDetails;
import jakarta.servlet.http.HttpServletRequest;
import com.example.dstay.news.Entity.News;
import com.example.dstay.news.Repository.NewsRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public NewsService(NewsRepository newsRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public NewsWithAuthorDetails execGetNewsById(Long newsId){
        Optional<News> news = newsRepository.findById(newsId);
        NewsWithAuthorDetails newsWithAuthorDetails = new NewsWithAuthorDetails();
        if(news.isPresent()){
            newsWithAuthorDetails.setNewsId(news.get().getId());
            newsWithAuthorDetails.setNewsTitle(news.get().getTitle());
            newsWithAuthorDetails.setNewsDescription(news.get().getDescription());
            newsWithAuthorDetails.setNewsCreatedAt(news.get().getCreated_at());
            newsWithAuthorDetails.setNewsVotes(news.get().getVotes());
            newsWithAuthorDetails.setCategories(news.get().getCategory().getName());
            newsWithAuthorDetails.setNewsUrl(news.get().getURL());

            newsWithAuthorDetails.setAuthorId(news.get().getAuthor().getId());
            newsWithAuthorDetails.setAuthorName(news.get().getAuthor().getName());
            newsWithAuthorDetails.setAuthorSurname(news.get().getAuthor().getSurname());
            newsWithAuthorDetails.setAuthorSpecialization(news.get().getAuthor().getSpecialization());
            newsWithAuthorDetails.setAuthorCreatedAt(news.get().getAuthor().getCreatedAt());
            return newsWithAuthorDetails;
        }
        return null;
    }

    public Long execUploadNews(NewsDTO newsDTO) {
        News news = new News();
        Optional<Category> category = categoryRepository.findById(newsDTO.getCategories());
        if (category.isPresent()){
            news.setAuthor(userResolver(newsDTO.getAuthor()));
            news.setCategory(category.get());
            news.setURL(newsDTO.getURL());
            news.setDescription(newsDTO.getDescription());
            news.setCreated_at(new Date());
            news.setTitle(newsDTO.getTitle());
        }

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

    public Page<CategoryNewsDTO> execGetNewsByCategory(String category, Pageable pageable){
        Optional<Category> category1 = categoryRepository.findByName(category);
        if (category1.isPresent()){
            try{
                Page<News> news = newsRepository.findByCategory_Name(category, pageable);
            return new PageImpl<>(news.stream()
                    .map(n -> {
                        CategoryNewsDTO categoryNewsDTO = new CategoryNewsDTO();
                        categoryNewsDTO.setTitle(n.getTitle());
                        categoryNewsDTO.setNews_id(n.getId());
                        categoryNewsDTO.setVotes(n.getVotes());
                        categoryNewsDTO.setAuthor_id(n.getAuthor().getId());
                        categoryNewsDTO.setCreatedAt(n.getCreated_at());
                        categoryNewsDTO.setAuthor_username(n.getAuthor().getUsername());
                        return categoryNewsDTO;
                    }).collect(Collectors.toList()), pageable, news.getTotalElements());
            }catch (Exception e){
                return new PageImpl<>(Collections.emptyList(), pageable, 0);
            }

        }else {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }
    }

    public Page<CategoryNewsDTO> execGetTopOfTheWeek(){
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
            Date startDate = calendar.getTime();

            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            Date endDate = calendar.getTime();
            PageRequest pageRequest = PageRequest.of(0, 10);
            Page<News> news = newsRepository.findTop10NewsByVotesInCurrentWeek(startDate, endDate, pageRequest);
            return new PageImpl<>(news.stream()
                    .map(n -> {
                        CategoryNewsDTO categoryNewsDTO = new CategoryNewsDTO();
                        categoryNewsDTO.setTitle(n.getTitle());
                        categoryNewsDTO.setNews_id(n.getId());
                        categoryNewsDTO.setVotes(n.getVotes());
                        categoryNewsDTO.setAuthor_id(n.getAuthor().getId());
                        categoryNewsDTO.setCreatedAt(n.getCreated_at());
                        categoryNewsDTO.setAuthor_username(n.getAuthor().getUsername());
                        return categoryNewsDTO;
                    }).collect(Collectors.toList()), pageRequest, news.getTotalElements());
        }catch (Exception e){
            return null;
        }
    }
}
