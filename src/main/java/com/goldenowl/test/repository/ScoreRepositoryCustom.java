package com.goldenowl.test.repository;

import com.goldenowl.test.DTO.response.TopScore;
import com.goldenowl.test.enums.SubjectGroup;

import java.util.List;

public interface ScoreRepositoryCustom {
    List<TopScore> findTop10ByGroup(SubjectGroup group);
}
