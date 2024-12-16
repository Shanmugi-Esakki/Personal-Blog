package com.example.PersonalBlog.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PersonalBlog.Entity.Comment;
import com.example.PersonalBlog.Entity.Blog;
import com.example.PersonalBlog.Repo.BlogRepo;
import com.example.PersonalBlog.Repo.commentRepo;

@Service
public class CommentService {

	 @Autowired
	    private BlogRepo blogRepository;

	    @Autowired
	    private commentRepo commentRepository;

	    public Comment addComment(Long blogId, Comment comment) {
	    	System.out.print("inside add comment 1");
	        Blog blog = blogRepository.findById(blogId)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid blog ID"));
	        System.out.print("inside add comment 2");
	        comment.setBl(blog);
	        comment.setPostedOn(LocalDateTime.now());
	        System.out.println(comment.getCId());
	        System.out.println(blog.getId());
		  
	        return commentRepository.save(comment);
	    }

	    public List<Comment> getComments(Long blogId) {
	        Blog blog = blogRepository.findById(blogId)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid blog ID"));
	        return blog.getComments();
	    }
}
