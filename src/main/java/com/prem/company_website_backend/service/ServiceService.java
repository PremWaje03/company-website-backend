package com.prem.company_website_backend.service;
import com.prem.company_website_backend.exception.ResourceNotFoundException;
import com.prem.company_website_backend.model.ServiceEntity;
import com.prem.company_website_backend.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository repository;

    public ServiceService(ServiceRepository repository) {
        this.repository = repository;
    }

    public ServiceEntity addService(ServiceEntity service) {
        return repository.save(service);
    }

    public List<ServiceEntity> getAllServices() {
        return repository.findAll();
    }

    public List<ServiceEntity> getActiveServices() {
        return repository.findByActiveTrue();
    }

    public void deleteService(String id) {
        repository.deleteById(id);
    }

    public ServiceEntity updateService(String id, ServiceEntity updated) {

        ServiceEntity existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setIcon(updated.getIcon());

        return repository.save(existing);
    }

    public ServiceEntity toggleService(String id) {

        ServiceEntity service = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        service.setActive(!service.isActive());
        return repository.save(service);
    }


}
