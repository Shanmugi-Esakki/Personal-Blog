package com.example.PersonalBlog.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PersonalBlog.Entity.Contact;
import com.example.PersonalBlog.Repo.contactRepo;

@Service
public class contactService {

    @Autowired
    private contactRepo contactRepository;

    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }
}
