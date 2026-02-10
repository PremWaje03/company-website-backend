package com.prem.company_website_backend.config;

import com.prem.company_website_backend.model.AdminEntity;
import com.prem.company_website_backend.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AdminSeeder {

    @Bean
    CommandLineRunner seedAdmin(
            AdminRepository repo,
            BCryptPasswordEncoder encoder) {

        return args -> {
            if (repo.findByEmail("admin@gmail.com").isEmpty()) {

                AdminEntity admin = new AdminEntity();
                admin.setEmail("admin@gmail.com");
                admin.setPassword(encoder.encode("admin123"));

                repo.save(admin);

                System.out.println("✅ ADMIN CREATED: admin@gmail.com / admin123");
            }
        };
    }
}
