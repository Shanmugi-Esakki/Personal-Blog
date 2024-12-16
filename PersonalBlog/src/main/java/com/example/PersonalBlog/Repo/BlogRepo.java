package com.example.PersonalBlog.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PersonalBlog.Entity.Blog;


@Repository
public interface BlogRepo extends JpaRepository<Blog, Long> {
    List<Blog> findByNameContainingOrContentContaining(String name, String content);
}
