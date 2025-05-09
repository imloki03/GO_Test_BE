package com.goldenowl.test.DTO.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectScoreStatistics {
    Long above8;
    Long from6To8;
    Long from4To6;
    Long below4;
}
