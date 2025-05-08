package com.goldenowl.test.DTO.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopScore {
    private String sbd;
    private Double totalScore;
    private Map<String, Double> subjectScores;
}
