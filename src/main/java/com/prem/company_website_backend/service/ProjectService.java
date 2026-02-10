package com.prem.company_website_backend.service;

import com.prem.company_website_backend.exception.ResourceNotFoundException;
import com.prem.company_website_backend.model.ProjectEntity;
import com.prem.company_website_backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    // ADD
    public ProjectEntity addProject(ProjectEntity project) {
        return repository.save(project);
    }

    // UPDATE
    public ProjectEntity updateProject(String id, ProjectEntity updated) {

        ProjectEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setTechnologies(updated.getTechnologies());
        existing.setProjectUrl(updated.getProjectUrl());
        existing.setImageUrl(updated.getImageUrl());
        existing.setFeatured(updated.isFeatured());

        return repository.save(existing);
    }

    // TOGGLE ACTIVE
    public ProjectEntity toggleProject(String id) {

        ProjectEntity project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        project.setActive(!project.isActive());
        return repository.save(project);
    }

    // ADMIN – ALL
    public List<ProjectEntity> getAllProjects() {
        return repository.findAll();
    }

    // PUBLIC – ACTIVE
    public List<ProjectEntity> getActiveProjects() {
        return repository.findByActiveTrue();
    }

    // PUBLIC – FEATURED
    public List<ProjectEntity> getFeaturedProjects() {
        return repository.findByFeaturedTrueAndActiveTrue();
    }

    // DELETE (optional)
    public void deleteProject(String id) {
        repository.deleteById(id);
    }
}
