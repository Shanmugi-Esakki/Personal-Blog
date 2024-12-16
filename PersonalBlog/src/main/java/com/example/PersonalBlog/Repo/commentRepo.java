package com.example.PersonalBlog.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PersonalBlog.Entity.Comment;

public interface commentRepo extends JpaRepository<Comment, Long> {
	
}
