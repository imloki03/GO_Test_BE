package com.goldenowl.test.service;

import com.goldenowl.test.DTO.response.ScoreStatistics;
import com.goldenowl.test.DTO.response.TopScore;
import com.goldenowl.test.enums.SubjectGroup;
import com.goldenowl.test.model.Score;

import java.util.List;

public interface ScoreService {
    Score getScore(String id);
    ScoreStatistics getScoreStatistics();
    List<TopScore> getTop10ByGroup(String group);
}
