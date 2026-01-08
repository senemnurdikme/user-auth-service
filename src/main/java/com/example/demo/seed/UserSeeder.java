package com.example.demo.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.*;

@Component
public class UserSeeder implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    private static final SecureRandom RNG = new SecureRandom();
    private static final char[] CHARS =
            "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789!@#$%&*_-+=".toCharArray();

    // 🔴 Plain password tutulacak kullanıcı indexleri
    private static final Set<Integer> PLAIN_USERS = Set.of(
            40_000,
            50_000,
            300_000,
            500_000,
            700_000
    );

    public UserSeeder(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        System.out.println(">>> USER SEEDER STARTED <<<");

        final int totalUsers = 1_000_000;
        final int batchSize = 10_000;

        Integer existing = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users",
                Integer.class
        );

        System.out.println("Existing users: " + existing);

        if (existing != null && existing >= totalUsers) {
            System.out.println("Users table already contains sufficient data. Seeding skipped.");
            return;
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < totalUsers; i += batchSize) {
            int from = i;
            int to = Math.min(i + batchSize, totalUsers);

            List<Object[]> batch = new ArrayList<>(batchSize);

            for (int j = from; j < to; j++) {

                String rawPassword = generatePassword(14);
                String hashedPassword = passwordEncoder.encode(rawPassword);

                String plainToStore = PLAIN_USERS.contains(j)
                        ? rawPassword
                        : null;

                batch.add(new Object[]{
                        "user" + j + "@test.com",
                        "Name" + j,
                        "Surname" + j,
                        hashedPassword,
                        plainToStore,
                        "U" + j
                });
            }

            jdbcTemplate.batchUpdate(
                    """
                    INSERT INTO users
                    (email, first_name, last_name, password, plain_password, user_no)
                    VALUES (?, ?, ?, ?, ?, ?)
                    """,
                    batch
            );

            if (i % 100_000 == 0) {
                System.out.println("Inserted users: " + i);
            }
        }

        System.out.println("✔ Seeding completed in " +
                (System.currentTimeMillis() - start) + " ms");
    }

    private static String generatePassword(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARS[RNG.nextInt(CHARS.length)]);
        }
        return sb.toString();
    }
}
