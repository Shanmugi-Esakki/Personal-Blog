package com.example.PersonalBlog.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.PersonalBlog.Entity.Blog;
import com.example.PersonalBlog.Repo.BlogRepo;

import java.util.List;
import java.util.Optional;

@Service
public class blogService {

    @Autowired
    private BlogRepo brepo;

    public void savePost(Blog post) {
        brepo.save(post);
    }

    public List<Blog> getAllPosts() {
        return brepo.findAll();
    }
    public Blog getPostById(Long id) {
        Optional<Blog> optionalBlog = brepo.findById(id);
        return optionalBlog.orElse(null); // Return null or throw an exception if not found
    }
  

        public List<Blog> searchPosts(String query) {
        	System.out.print("inside search query service");
            return brepo.findByNameContainingOrContentContaining(query, query);
        }
        public void deletePostById(Long id) {
            Optional<Blog> optionalBlog = brepo.findById(id);
            if (optionalBlog.isPresent()) {
                brepo.delete(optionalBlog.get());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found");
            }
        }

}
