package com.example.dstay.admin.activity.Service;


import com.example.dstay.admin.AdminUtils;
import com.example.dstay.comments.Repository.CommentRepository;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.news.DTOs.NewsWithAuthorDetails;
import com.example.dstay.news.Entity.News;
import com.example.dstay.news.Repository.NewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ActivityAdministrationService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public ActivityAdministrationService(NewsRepository newsRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public Page<NewsWithAuthorDetails> execGetNewsList(String token, Pageable pageable){
        if(AdminUtils.adminRoleCheck(token, userRepository)){
            Page<News> news = newsRepository.findAll(pageable);
            return new PageImpl<>(news.stream()
                    .map(n -> {
                        NewsWithAuthorDetails newsWithAuthorDetails = new NewsWithAuthorDetails();
                        newsWithAuthorDetails.setNewsId(n.getId());
                        newsWithAuthorDetails.setAuthorId(n.getAuthor().getId());
                        newsWithAuthorDetails.setNewsVotes(n.getVotes());
                        newsWithAuthorDetails.setNewsTitle(n.getTitle());
                        newsWithAuthorDetails.setNewsDescription(n.getDescription());
                        newsWithAuthorDetails.setNewsCreatedAt(n.getCreated_at());
                        newsWithAuthorDetails.setNewsUrl(n.getURL());
                        return newsWithAuthorDetails;
                    }).collect(Collectors.toList()), pageable, news.getTotalElements());
        }
        return null;
    }
}
