package com.goldenowl.test.repository;

import com.goldenowl.test.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, String>, ScoreRepositoryCustom {
    @Query("SELECT " +
            "  SUM(CASE WHEN s.toan >= 8 THEN 1 ELSE 0 END) AS toanAbove8, " +
            "  SUM(CASE WHEN s.toan >= 6 AND s.toan < 8 THEN 1 ELSE 0 END) AS toan6To8, " +
            "  SUM(CASE WHEN s.toan >= 4 AND s.toan < 6 THEN 1 ELSE 0 END) AS toan4To6, " +
            "  SUM(CASE WHEN s.toan < 4 THEN 1 ELSE 0 END) AS toanBelow4, " +

            "  SUM(CASE WHEN s.nguVan >= 8 THEN 1 ELSE 0 END) AS nguVanAbove8, " +
            "  SUM(CASE WHEN s.nguVan >= 6 AND s.nguVan < 8 THEN 1 ELSE 0 END) AS nguVan6To8, " +
            "  SUM(CASE WHEN s.nguVan >= 4 AND s.nguVan < 6 THEN 1 ELSE 0 END) AS nguVan4To6, " +
            "  SUM(CASE WHEN s.nguVan < 4 THEN 1 ELSE 0 END) AS nguVanBelow4, " +

            "  SUM(CASE WHEN s.ngoaiNgu >= 8 THEN 1 ELSE 0 END) AS ngoaiNguAbove8, " +
            "  SUM(CASE WHEN s.ngoaiNgu >= 6 AND s.ngoaiNgu < 8 THEN 1 ELSE 0 END) AS ngoaiNgu6To8, " +
            "  SUM(CASE WHEN s.ngoaiNgu >= 4 AND s.ngoaiNgu < 6 THEN 1 ELSE 0 END) AS ngoaiNgu4To6, " +
            "  SUM(CASE WHEN s.ngoaiNgu < 4 THEN 1 ELSE 0 END) AS ngoaiNguBelow4, " +

            "  SUM(CASE WHEN s.vatLi >= 8 THEN 1 ELSE 0 END) AS vatLiAbove8, " +
            "  SUM(CASE WHEN s.vatLi >= 6 AND s.vatLi < 8 THEN 1 ELSE 0 END) AS vatLi6To8, " +
            "  SUM(CASE WHEN s.vatLi >= 4 AND s.vatLi < 6 THEN 1 ELSE 0 END) AS vatLi4To6, " +
            "  SUM(CASE WHEN s.vatLi < 4 THEN 1 ELSE 0 END) AS vatLiBelow4, " +

            "  SUM(CASE WHEN s.hoaHoc >= 8 THEN 1 ELSE 0 END) AS hoaHocAbove8, " +
            "  SUM(CASE WHEN s.hoaHoc >= 6 AND s.hoaHoc < 8 THEN 1 ELSE 0 END) AS hoaHoc6To8, " +
            "  SUM(CASE WHEN s.hoaHoc >= 4 AND s.hoaHoc < 6 THEN 1 ELSE 0 END) AS hoaHoc4To6, " +
            "  SUM(CASE WHEN s.hoaHoc < 4 THEN 1 ELSE 0 END) AS hoaHocBelow4, " +

            "  SUM(CASE WHEN s.sinhHoc >= 8 THEN 1 ELSE 0 END) AS sinhHocAbove8, " +
            "  SUM(CASE WHEN s.sinhHoc >= 6 AND s.sinhHoc < 8 THEN 1 ELSE 0 END) AS sinhHoc6To8, " +
            "  SUM(CASE WHEN s.sinhHoc >= 4 AND s.sinhHoc < 6 THEN 1 ELSE 0 END) AS sinhHoc4To6, " +
            "  SUM(CASE WHEN s.sinhHoc < 4 THEN 1 ELSE 0 END) AS sinhHocBelow4, " +

            "  SUM(CASE WHEN s.lichSu >= 8 THEN 1 ELSE 0 END) AS lichSuAbove8, " +
            "  SUM(CASE WHEN s.lichSu >= 6 AND s.lichSu < 8 THEN 1 ELSE 0 END) AS lichSu6To8, " +
            "  SUM(CASE WHEN s.lichSu >= 4 AND s.lichSu < 6 THEN 1 ELSE 0 END) AS lichSu4To6, " +
            "  SUM(CASE WHEN s.lichSu < 4 THEN 1 ELSE 0 END) AS lichSuBelow4, " +

            "  SUM(CASE WHEN s.diaLi >= 8 THEN 1 ELSE 0 END) AS diaLiAbove8, " +
            "  SUM(CASE WHEN s.diaLi >= 6 AND s.diaLi < 8 THEN 1 ELSE 0 END) AS diaLi6To8, " +
            "  SUM(CASE WHEN s.diaLi >= 4 AND s.diaLi < 6 THEN 1 ELSE 0 END) AS diaLi4To6, " +
            "  SUM(CASE WHEN s.diaLi < 4 THEN 1 ELSE 0 END) AS diaLiBelow4, " +

            "  SUM(CASE WHEN s.gdcd >= 8 THEN 1 ELSE 0 END) AS gdcdAbove8, " +
            "  SUM(CASE WHEN s.gdcd >= 6 AND s.gdcd < 8 THEN 1 ELSE 0 END) AS gdcd6To8, " +
            "  SUM(CASE WHEN s.gdcd >= 4 AND s.gdcd < 6 THEN 1 ELSE 0 END) AS gdcd4To6, " +
            "  SUM(CASE WHEN s.gdcd < 4 THEN 1 ELSE 0 END) AS gdcdBelow4 " +

            "FROM Score s")
    List<Object[]> getScoreStatistics();
}