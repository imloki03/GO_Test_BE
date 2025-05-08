package com.goldenowl.test.service.impl;

import com.goldenowl.test.DTO.response.ScoreStatistics;
import com.goldenowl.test.DTO.response.SubjectScoreStatistics;
import com.goldenowl.test.DTO.response.TopScore;
import com.goldenowl.test.enums.SubjectGroup;
import com.goldenowl.test.exception.AppException;
import com.goldenowl.test.model.Score;
import com.goldenowl.test.repository.ScoreRepository;
import com.goldenowl.test.service.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {
    final ScoreRepository scoreRepository;


    @Override
    public Score getScore(String id) {
        if (!id.matches("^\\d{8}$"))
            throw new AppException(HttpStatus.BAD_REQUEST, "Registration number must have 8 digits");

        return scoreRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND,
                                "This registration number doesn't exist!"));
    }

    @Override
    public ScoreStatistics getScoreStatistics() {
        List<Object[]> results = scoreRepository.getScoreStatistics();
        Map<String, SubjectScoreStatistics> statistics = new HashMap<>();

        String[] subjects = {"toan", "nguVan", "ngoaiNgu", "vatLi", "hoaHoc", "sinhHoc", "lichSu", "diaLi", "gdcd"};

        for (int i = 0; i < subjects.length; i++) {
            SubjectScoreStatistics subjectStats = SubjectScoreStatistics.builder()
                    .above8((Long) results.get(0)[i * 4])
                    .from6To8((Long) results.get(0)[i * 4 + 1])
                    .from4To6((Long) results.get(0)[i * 4 + 2])
                    .below4((Long) results.get(0)[i * 4 + 3])
                    .build();

            statistics.put(subjects[i], subjectStats);
        }

        return ScoreStatistics.builder()
                .subjectStatistics(statistics)
                .build();
    }

    @Override
    public List<TopScore> getTop10ByGroup(String group) {
        SubjectGroup subjectGroup;
        try {
             subjectGroup = SubjectGroup.fromString(group);
        } catch (IllegalArgumentException e) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Group not found!");
        }
        return scoreRepository.findTop10ByGroup(subjectGroup);
    }
}
