package ua.foxminded.vasilmartsyniuk.consoleapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ua.foxminded.vasilmartsyniuk.consoleapp.model.DataGenerate;

@Service
public class DataGenerateService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataGenerateService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void generateDataIfEmpty() {
        if (isDatabaseEmpty()) {
            DataGenerate dataGenerate = new DataGenerate(jdbcTemplate);
            dataGenerate.executeDataGeneration();
        } else {
            System.out.println("Database is not empty.");
        }
    }

    private boolean isDatabaseEmpty() {
        String countQuery = "SELECT COUNT(*) FROM students";
        Integer count = jdbcTemplate.queryForObject(countQuery, Integer.class);
        return count != null && count == 0;
    }
}
