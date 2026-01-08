package com.example.demo.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserSeeder implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;


    private final PasswordEncoder seedEncoder = new BCryptPasswordEncoder(4);

    private static final SecureRandom RNG = new SecureRandom();
    private static final char[] CHARS =
            "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789!@#$%&*_-+=".toCharArray();

    public UserSeeder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        System.out.println(">>> USER SEEDER STARTED <<<");

        final int totalUsers = 1_000_000;
        final int batchSize = 10_000;

        Integer existingCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
        System.out.println("Existing users: " + existingCount);

        if (existingCount != null && existingCount >= totalUsers) {
            System.out.println("Users table already contains sufficient data. Seeding skipped.");
            return;
        }

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < totalUsers; i += batchSize) {
            int from = i;
            int to = Math.min(i + batchSize, totalUsers);

            System.out.println("Preparing batch: " + from + " -> " + (to - 1));

            List<Object[]> batch = new ArrayList<>(to - from);

            for (int j = from; j < to; j++) {
                String rawPassword = generatePassword(14);
                String hashedPassword = seedEncoder.encode(rawPassword);

                batch.add(new Object[]{
                        "user" + j + "@test.com",
                        "Name" + j,
                        "Surname" + j,
                        hashedPassword,
                        "U" + j
                });
            }

            System.out.println("Hashing done. Inserting batch...");
            int[] res = jdbcTemplate.batchUpdate(
                    "INSERT INTO users (email, first_name, last_name, password, user_no) VALUES (?, ?, ?, ?, ?)",
                    batch
            );
            System.out.println("Inserted rows in this batch: " + res.length);

            if (i % 100_000 == 0) {
                Integer c = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
                System.out.println("CHECK COUNT after insert: " + c);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("✔ " + totalUsers + " users inserted successfully.");
        System.out.println("Total time (ms): " + (endTime - startTime));
    }

    private static String generatePassword(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int k = 0; k < length; k++) {
            sb.append(CHARS[RNG.nextInt(CHARS.length)]);
        }
        return sb.toString();
    }
}
