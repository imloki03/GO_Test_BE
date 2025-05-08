package com.goldenowl.test.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @Id
    private String sbd;

    private Double toan;
    private Double nguVan;
    private Double ngoaiNgu;
    private Double vatLi;
    private Double hoaHoc;
    private Double sinhHoc;
    private Double lichSu;
    private Double diaLi;
    private Double gdcd;
    private String maNgoaiNgu;
}
