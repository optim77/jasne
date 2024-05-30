package com.example.dstay.votes.Controller;

import com.example.dstay.votes.DTO.RateDTO;
import com.example.dstay.votes.Services.RateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
@RestResource
public class VoteController {

    private final RateService rateService;

    public VoteController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping("/vote_news")
    public ResponseEntity<HttpStatus> voteNew(@RequestBody RateDTO rateDTO){
        return rateService.execVoteNews(rateDTO);
    }

    @PostMapping("/vote_comment")
    public ResponseEntity<HttpStatus> voteComment(@RequestBody RateDTO rateDTO){
        return  rateService.execVoteComment(rateDTO);
    }

}
