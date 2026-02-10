package com.prem.company_website_backend.service;

import com.prem.company_website_backend.exception.ResourceNotFoundException;
import com.prem.company_website_backend.model.TeamMemberEntity;
import com.prem.company_website_backend.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeamMemberService {

    private final TeamMemberRepository repository;

    public TeamMemberService(TeamMemberRepository repository) {
        this.repository = repository;
    }

    // ADD
    public TeamMemberEntity addMember(TeamMemberEntity member) {
        return repository.save(member);
    }

    // UPDATE
    public TeamMemberEntity updateMember(String id, TeamMemberEntity updated) {

        TeamMemberEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team member not found"));

        existing.setName(updated.getName());
        existing.setRole(updated.getRole());
        existing.setBio(updated.getBio());
        existing.setPhotoUrl(updated.getPhotoUrl());
        existing.setSocialLinks(updated.getSocialLinks());

        return repository.save(existing);
    }

    // TOGGLE ACTIVE
    public TeamMemberEntity toggleMember(String id) {

        TeamMemberEntity member = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team member not found"));

        member.setActive(!member.isActive());
        return repository.save(member);
    }

    // ADMIN – ALL
    public List<TeamMemberEntity> getAllMembers() {
        return repository.findAll();
    }

    // PUBLIC – ACTIVE ONLY
    public List<TeamMemberEntity> getActiveMembers() {
        return repository.findByActiveTrue();
    }

    // DELETE (optional)
    public void deleteMember(String id) {
        repository.deleteById(id);
    }
}
