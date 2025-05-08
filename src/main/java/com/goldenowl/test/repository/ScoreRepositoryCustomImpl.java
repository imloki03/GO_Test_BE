package com.goldenowl.test.repository;

import com.goldenowl.test.DTO.response.TopScore;
import com.goldenowl.test.enums.SubjectGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ScoreRepositoryCustomImpl implements  ScoreRepositoryCustom{
    final JdbcTemplate jdbcTemplate;

    @Override
    public List<TopScore> findTop10ByGroup(SubjectGroup group) {
        String[] subjects = group.getSubjects();

        String select = String.join(", ", subjects);
        String total = Arrays.stream(subjects)
                .map(s -> "IFNULL(" + s + ", 0)")
                .collect(Collectors.joining(" + "));

        String sql = String.format("""
            SELECT sbd, %s, (%s) AS total
            FROM score
            ORDER BY total DESC
            LIMIT 10
        """, select, total);

        return jdbcTemplate.query(sql, (rs, i) -> {
            Map<String, Double> scores = new HashMap<>();
            for (String s : subjects) scores.put(s, rs.getDouble(s));
            return TopScore.builder()
                    .sbd(rs.getString("sbd"))
                    .subjectScores(scores)
                    .totalScore(rs.getDouble("total"))
                    .build();
        });
    }
}
