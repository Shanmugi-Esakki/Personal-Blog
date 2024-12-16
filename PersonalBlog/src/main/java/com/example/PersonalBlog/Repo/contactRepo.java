package com.example.PersonalBlog.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PersonalBlog.Entity.Contact;
public interface contactRepo extends JpaRepository<Contact, Long> {
}
