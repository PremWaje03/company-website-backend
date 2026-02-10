package com.prem.company_website_backend.service;

import com.prem.company_website_backend.exception.ResourceNotFoundException;
import com.prem.company_website_backend.model.TestimonialEntity;
import com.prem.company_website_backend.repository.TestimonialRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TestimonialService {

    private final TestimonialRepository repository;

    public TestimonialService(TestimonialRepository repository) {
        this.repository = repository;
    }

    // ADD
    public TestimonialEntity addTestimonial(TestimonialEntity testimonial) {
        return repository.save(testimonial);
    }

    // UPDATE
    public TestimonialEntity updateTestimonial(String id, TestimonialEntity updated) {

        TestimonialEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Testimonial not found"));

        existing.setClientName(updated.getClientName());
        existing.setClientRole(updated.getClientRole());
        existing.setMessage(updated.getMessage());
        existing.setRating(updated.getRating());
        existing.setPhotoUrl(updated.getPhotoUrl());

        return repository.save(existing);
    }

    // TOGGLE ACTIVE
    public TestimonialEntity toggleTestimonial(String id) {

        TestimonialEntity testimonial = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Testimonial not found"));

        testimonial.setActive(!testimonial.isActive());
        return repository.save(testimonial);
    }

    // ADMIN – ALL
    public List<TestimonialEntity> getAllTestimonials() {
        return repository.findAll();
    }

    // PUBLIC – ACTIVE ONLY
    public List<TestimonialEntity> getActiveTestimonials() {
        return repository.findByActiveTrue();
    }

    // DELETE (optional)
    public void deleteTestimonial(String id) {
        repository.deleteById(id);
    }
}
