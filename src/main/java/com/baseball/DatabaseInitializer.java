package com.baseball;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        try {
            jdbcTemplate.execute("ALTER TABLE line_up_name_info DROP FOREIGN KEY FK5mxldl0u6r8wf26ejm51n27h6;");
            jdbcTemplate.execute("ALTER TABLE line_up_name_info ADD CONSTRAINT FK5mxldl0u6r8wf26ejm51n27h6 FOREIGN KEY (diary_info) REFERENCES diary_info(diary_id);");
        } catch (Exception e) {
            // Handle exception or log the error
            System.err.println("Database initialization failed: " + e.getMessage());
        }
    }

}
