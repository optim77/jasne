package com.example.dstay.news.Controller;

import com.example.dstay.categories.Entity.Category;
import com.example.dstay.news.DTOs.CategoryNewsDTO;
import com.example.dstay.news.DTOs.NewsDTO;
import com.example.dstay.news.DTOs.NewsWithAuthorDetails;
import com.example.dstay.news.Entity.News;
import com.example.dstay.news.Repository.NewsRepository;
import com.example.dstay.news.Services.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
@RestResource
public class NewsControllers {

    private final NewsRepository newsRepository;
    private final NewsService newsService;

    public NewsControllers(NewsRepository newsRepository, NewsService newsService) {
        this.newsRepository = newsRepository;
        this.newsService = newsService;
    }

    @GetMapping("/news/{newsId}")
    public NewsWithAuthorDetails getNewsById(@PathVariable Long newsId){
        return newsService.execGetNewsById(newsId);
    }

    @GetMapping("/news")
    public Page<News> getListOfNews(@RequestBody String phrase,  Pageable pageable){
        return newsRepository.findAllByTitle(phrase, pageable);
    }

    @PostMapping("/news")
    public Long uploadNews(@RequestBody NewsDTO newsDTO){
        return newsService.execUploadNews(newsDTO);
    }

    @DeleteMapping("/delete/news/{news_id}")
    public ResponseEntity<HttpStatus> deleteNews(@PathVariable Long news_id) throws Exception {
        return newsService.execDeleteNews(news_id);
    }

    @PatchMapping("/update/news/{news_id}")
    public News updateNews(@PathVariable Long news_id, @RequestBody News news) throws ChangeSetPersister.NotFoundException {
        return newsService.execUpdateNews(news_id, news);
    }

    @GetMapping("/news/category/{category}")
    public Page<CategoryNewsDTO> getNewsByCategory(@PathVariable String category, Pageable pageable){
        return newsService.execGetNewsByCategory(category, pageable);
    }

    @GetMapping("/top_week_all")
    public Page<CategoryNewsDTO> getTopOfTheWeek(){
        return newsService.execGetTopOfTheWeek();
    }
}
