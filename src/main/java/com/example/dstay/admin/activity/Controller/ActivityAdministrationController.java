package com.example.dstay.admin.activity.Controller;

import com.example.dstay.admin.activity.Service.ActivityAdministrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
@RestResource
public class ActivityAdministrationController {

    private final ActivityAdministrationService activityAdministrationService;

    public ActivityAdministrationController(ActivityAdministrationService activityAdministrationService) {
        this.activityAdministrationService = activityAdministrationService;
    }
}
