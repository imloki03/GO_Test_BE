package com.goldenowl.test.enums;

import lombok.Getter;

@Getter
public enum SubjectGroup {
    A("toan", "vat_li", "hoa_hoc"),
    A1("toan", "vat_li", "ngoai_ngu"),
    B("toan", "hoa_hoc", "sinh_hoc"),
    C("nguVan", "lich_su", "dia_li"),
    D("toan", "ngu_van", "ngoai_ngu");

    private final String[] subjects;

    SubjectGroup(String... subjects) {
        this.subjects = subjects;
    }

    public static SubjectGroup fromString(String input) {
        return SubjectGroup.valueOf(input.toUpperCase());
    }
}
