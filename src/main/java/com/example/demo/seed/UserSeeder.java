package com.example.demo.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class UserSeeder implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public UserSeeder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {

        int totalUsers = 1_000_000;
        int batchSize = 10_000;

        Integer existingCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users",
                Integer.class
        );

        if (existingCount != null && existingCount >= totalUsers) {
            System.out.println("Users table already contains sufficient data. Seeding skipped.");
            return;
        }

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < totalUsers; i += batchSize) {
            int from = i;
            int to = Math.min(i + batchSize, totalUsers);

            List<Object[]> batch = new ArrayList<>(to - from);

            for (int j = from; j < to; j++) {
                batch.add(new Object[]{
                        "user" + j + "@test.com",
                        "Name" + j,
                        "Surname" + j,
                        "1234",
                        "U" + j
                });
            }

            jdbcTemplate.batchUpdate(
                    "INSERT INTO users (email, first_name, last_name, password, user_no) VALUES (?, ?, ?, ?, ?)",
                    batch
            );

            if (i % 100_000 == 0) {
                System.out.println("Inserted records: " + i);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("✔ 1,000,000 users inserted successfully.");
        System.out.println("Total time (ms): " + (endTime - startTime));
    }
}
