package com.goldenowl.test.controller;

import com.goldenowl.test.DTO.RespondData;
import com.goldenowl.test.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("scores")
public class ScoreController {
    final ScoreService scoreService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getScore(@PathVariable String id){
        var score = scoreService.getScore(id);
        var respondData = RespondData.builder()
                .status(HttpStatus.OK.value())
                .data(score)
                .desc("Get score for "+ id + " successfully!")
                .build();
        return new ResponseEntity<>(respondData, HttpStatus.OK);
    }

    @GetMapping("/stat")
    public ResponseEntity<?> getScoreStatistics(){
        var stat = scoreService.getScoreStatistics();
        var respondData = RespondData.builder()
                .status(HttpStatus.OK.value())
                .data(stat)
                .desc("Get score statistics successfully!")
                .build();
        return new ResponseEntity<>(respondData, HttpStatus.OK);
    }
    @GetMapping("/top10/{group}")
    public ResponseEntity<?> getTop10ByGroup(@PathVariable String group){
        var top10 = scoreService.getTop10ByGroup(group);
        var respondData = RespondData.builder()
                .status(HttpStatus.OK.value())
                .data(top10)
                .desc("Get top 10 of group "+ group +" successfully!")
                .build();
        return new ResponseEntity<>(respondData, HttpStatus.OK);
    }
}
