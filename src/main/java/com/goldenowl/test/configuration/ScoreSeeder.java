package com.goldenowl.test.configuration;

import com.goldenowl.test.model.Score;
import com.goldenowl.test.repository.ScoreRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScoreSeeder implements CommandLineRunner {
    final DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM score")) {
                rs.next();
                if (rs.getInt(1) > 0) {
                    return;
                }
            }

            conn.setAutoCommit(false);

            String sql = "INSERT INTO score (sbd, toan, ngu_van, ngoai_ngu, vat_li, hoa_hoc, sinh_hoc, lich_su, dia_li, gdcd, ma_ngoai_ngu) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 Reader reader = new FileReader("src/main/resources/diem_thi_thpt_2024.csv");
                 CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {

                int batchSize = 1000;
                int count = 0;
                String[] nextLine;

                while ((nextLine = csvReader.readNext()) != null) {
                    ps.setString(1, nextLine[0]);
                    ps.setObject(2, parseDouble(nextLine[1]), Types.DOUBLE);
                    ps.setObject(3, parseDouble(nextLine[2]), Types.DOUBLE);
                    ps.setObject(4, parseDouble(nextLine[3]), Types.DOUBLE);
                    ps.setObject(5, parseDouble(nextLine[4]), Types.DOUBLE);
                    ps.setObject(6, parseDouble(nextLine[5]), Types.DOUBLE);
                    ps.setObject(7, parseDouble(nextLine[6]), Types.DOUBLE);
                    ps.setObject(8, parseDouble(nextLine[7]), Types.DOUBLE);
                    ps.setObject(9, parseDouble(nextLine[8]), Types.DOUBLE);
                    ps.setObject(10, parseDouble(nextLine[9]), Types.DOUBLE);
                    ps.setString(11, nextLine[10]);

                    ps.addBatch();
                    count++;

                    if (count % batchSize == 0) {
                        ps.executeBatch();
                        conn.commit();
                        ps.clearBatch();
                        System.out.println(new Date() + " : Inserted 1000 rows");
                    }
                }

                if (count % batchSize != 0) {
                    ps.executeBatch();
                    System.out.println(new Date() + " : Inserted remaining rows");
                }

                conn.setAutoCommit(true);
            }
        }
    }

    private Double parseDouble(String s) {
        try {
            return (s == null || s.isEmpty()) ? null : Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
