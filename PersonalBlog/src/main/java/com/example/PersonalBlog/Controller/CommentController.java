package com.example.PersonalBlog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.PersonalBlog.Entity.Comment;
import com.example.PersonalBlog.Service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/blogs/{blogId}/comments")
    public Comment addComment(@PathVariable("blogId") Long blogId, @RequestBody Comment comment) {
    	System.out.print("inside post control");
    	System.out.print(comment.getText());
        return commentService.addComment(blogId, comment);
    }

    @GetMapping("/blogs/{blogId}/comments")
    public List<Comment> getComments(@PathVariable("blogId") Long blogId) {
    	System.out.print("inside get control");
        return commentService.getComments(blogId);
    }
    @PostMapping("/blogs/comments")
    public String addComment1( @RequestBody Comment comment) {
    	System.out.print("inside post control");
//        return commentService.addCommentService( comment);
    	return "new function";
    }
}

