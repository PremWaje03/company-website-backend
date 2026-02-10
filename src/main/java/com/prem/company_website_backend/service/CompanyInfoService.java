package com.prem.company_website_backend.service;

import com.prem.company_website_backend.model.CompanyInfoEntity;
import com.prem.company_website_backend.repository.CompanyInfoRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CompanyInfoService {

    private final CompanyInfoRepository repository;

    public CompanyInfoService(CompanyInfoRepository repository) {
        this.repository = repository;
    }

    // GET company info (public & admin both)
    public CompanyInfoEntity getCompanyInfo() {
        return repository.findAll()
                .stream()
                .findFirst()
                .orElse(null);
    }

    // CREATE or UPDATE (ADMIN)
    public CompanyInfoEntity saveOrUpdate(CompanyInfoEntity info) {

        Optional<CompanyInfoEntity> existing =
                repository.findAll().stream().findFirst();

        if (existing.isPresent()) {
            CompanyInfoEntity old = existing.get();

            old.setCompanyName(info.getCompanyName());
            old.setAbout(info.getAbout());
            old.setEmail(info.getEmail());
            old.setPhone(info.getPhone());
            old.setAddress(info.getAddress());
            old.setSocialLinks(info.getSocialLinks());

            return repository.save(old);
        }

        return repository.save(info);
    }
}
