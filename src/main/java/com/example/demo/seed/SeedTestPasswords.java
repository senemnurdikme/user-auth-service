package com.example.demo.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedTestPasswords implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public SeedTestPasswords(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {


        List<String> emails = List.of(
                "user300000@test.com",
                "user400000@test.com",
                "user500000@test.com"
        );

        String raw = "Test123!";
        String hash = passwordEncoder.encode(raw);

        for (String email : emails) {
            int updated = jdbcTemplate.update(
                    "UPDATE users SET plain_password = ?, password = ? WHERE email = ?",
                    raw, hash, email
            );
            System.out.println("Updated " + email + " -> " + updated);
        }

        System.out.println("✅ Test users patched (plain + hash set).");
    }
}
