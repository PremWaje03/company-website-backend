package com.prem.company_website_backend.service;

import com.prem.company_website_backend.model.AdminEntity;
import com.prem.company_website_backend.repository.AdminRepository;
import com.prem.company_website_backend.exception.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository repository;
    private final BCryptPasswordEncoder encoder;

    public AdminService(AdminRepository repository,
                        BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public AdminEntity authenticate(String email, String password) {

        AdminEntity admin = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid credentials"));

        if (!encoder.matches(password, admin.getPassword())) {
            throw new ResourceNotFoundException("Invalid credentials");
        }

        return admin;
    }
}
