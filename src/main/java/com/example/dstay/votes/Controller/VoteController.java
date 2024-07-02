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
@RestResource
public class VoteController {

    private final RateService rateService;

    public VoteController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping("/make_vote")
    public ResponseEntity<Integer> voteNew(@RequestBody RateDTO rateDTO){
        if (rateDTO.getNews_id() != null){
            return rateService.execVoteNews(rateDTO);
        }
        else if(rateDTO.getComment_id() != null){
            return  rateService.execVoteComment(rateDTO);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
