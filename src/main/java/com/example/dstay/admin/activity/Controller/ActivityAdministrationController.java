package com.example.dstay.admin.activity.Controller;

import com.example.dstay.admin.AdminUtils;
import com.example.dstay.admin.activity.Service.ActivityAdministrationService;
import com.example.dstay.admin.user.DTO.AdminAuthDTO;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.news.DTOs.NewsWithAuthorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")

public class ActivityAdministrationController {

    private final ActivityAdministrationService activityAdministrationService;
    private final UserRepository userRepository;

    public ActivityAdministrationController(ActivityAdministrationService activityAdministrationService, UserRepository userRepository) {
        this.activityAdministrationService = activityAdministrationService;
        this.userRepository = userRepository;
    }

    @PostMapping("/admin/panel")
    public ResponseEntity<HttpStatus> adminMain(@RequestBody AdminAuthDTO token){
        // main admin panel
        // possible development with data from the service like day's posts, etc.
        if (AdminUtils.adminRoleCheck(token.getToken(), userRepository)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    @PostMapping("/admin/panel/news")
    public Page<NewsWithAuthorDetails> getNewsList(@RequestBody AdminAuthDTO token, Pageable pageable){
        return activityAdministrationService.execGetNewsList(token.getToken(), pageable);
    }
}
