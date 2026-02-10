package com.prem.company_website_backend.service;

import com.prem.company_website_backend.exception.ResourceNotFoundException;
import com.prem.company_website_backend.model.ContactEntity;
import com.prem.company_website_backend.repository.ContactRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    // WEBSITE – SAVE ENQUIRY
    public ContactEntity saveContact(ContactEntity contact) {
        return repository.save(contact);
    }

    // ADMIN – ALL ENQUIRIES
    public List<ContactEntity> getAllContacts() {
        return repository.findAll();
    }

    // ADMIN – FILTER BY STATUS
    public List<ContactEntity> getContactsByStatus(String status) {
        return repository.findByStatus(status);
    }

    // ADMIN – UPDATE STATUS
    public ContactEntity updateStatus(String id, String status) {

        ContactEntity contact = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));

        contact.setStatus(status);
        return repository.save(contact);
    }
}
