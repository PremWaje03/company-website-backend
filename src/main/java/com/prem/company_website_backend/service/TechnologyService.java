package com.prem.company_website_backend.service;


import com.prem.company_website_backend.exception.ResourceNotFoundException;
import com.prem.company_website_backend.model.TechnologyEntity;
import com.prem.company_website_backend.repository.TechnologyRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TechnologyService {

    private final TechnologyRepository repository;

    public TechnologyService(TechnologyRepository repository) {
        this.repository = repository;
    }

    // ADD
    public TechnologyEntity addTechnology(TechnologyEntity tech) {
        return repository.save(tech);
    }

    // UPDATE
    public TechnologyEntity updateTechnology(String id, TechnologyEntity updated) {
        TechnologyEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Technology not found"));

        existing.setName(updated.getName());
        existing.setIcon(updated.getIcon());

        return repository.save(existing);
    }

    // TOGGLE ACTIVE
    public TechnologyEntity toggleTechnology(String id) {
        TechnologyEntity tech = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Technology not found"));

        tech.setActive(!tech.isActive());
        return repository.save(tech);
    }

    // ADMIN – ALL
    public List<TechnologyEntity> getAllTechnologies() {
        return repository.findAll();
    }

    // PUBLIC – ACTIVE ONLY
    public List<TechnologyEntity> getActiveTechnologies() {
        return repository.findByActiveTrue();
    }

    // DELETE (Optional)
    public void deleteTechnology(String id) {
        repository.deleteById(id);
    }
}

