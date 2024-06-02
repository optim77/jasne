package com.example.dstay.admin.activity.Service;


import com.example.dstay.comments.Repository.CommentRepository;
import com.example.dstay.news.Repository.NewsRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivityAdministrationService {

    private final NewsRepository newsRepository;
    private final CommentRepository commentRepository;

    public ActivityAdministrationService(NewsRepository newsRepository, CommentRepository commentRepository) {
        this.newsRepository = newsRepository;
        this.commentRepository = commentRepository;
    }
}
