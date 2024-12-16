package com.example.PersonalBlog.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.example.PersonalBlog.Entity.Contact;
import com.example.PersonalBlog.Entity.Blog;
import com.example.PersonalBlog.Repo.BlogRepo;
import com.example.PersonalBlog.Service.blogService;
import com.example.PersonalBlog.Service.contactService;
@RestController
public class BlogController {
	 @Autowired
	    private blogService bservice;
	
	 @GetMapping("api/create")
	    public ModelAndView getCreate() {
	    	 System.out.println("Hello in create");
	        
	        return new ModelAndView("create");
	    }
	
	 @GetMapping("api/insta")
	    public ModelAndView getInsta() {
	    	 System.out.println("Hello in insta");
	        
	        return new ModelAndView("insta");
	    }
	 @GetMapping("api/search")
	    public ModelAndView getSearch() {
	    	 System.out.println("Hello in search");
	        
	        return new ModelAndView("search");
	    }
	 @GetMapping("api/facebook")
	    public ModelAndView getFacebook() {
	    	 System.out.println("Hello in fb");
	        
	        return new ModelAndView("facebook");
	    }
	 
	

	    private static String UPLOADED_FOLDER = "src/main/resources/static/imgs/";

	    
	    @GetMapping("api/view")
	    public ModelAndView getView(Model m) {
	    	 System.out.println("Hello in view");
	    	 List<Blog> posts = bservice.getAllPosts();
		        if (posts.isEmpty()) {
		            System.out.println("No data found.");
		        }
		        
		        m.addAttribute("blogs", posts);
		        
			
	        return new ModelAndView("view");
	    }

	    @PostMapping("/create-post")
	    public ModelAndView handlePostSubmission(
	            @RequestParam("post-name") String name,
	            @RequestParam("post-desc") String desc,
	            @RequestParam("post-content") String content,
	            @RequestParam("posted-by") String postedBy,
	            @RequestParam("tags") String tags,
	            @RequestParam("post-image") MultipartFile file,
	            Model m) {

	        String imageName = file.getOriginalFilename();
	        Path path = Paths.get(UPLOADED_FOLDER+ imageName);

	        try {
	            Files.write(path, file.getBytes());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        Blog post = new Blog();
	        post.setName(name);
	        post.setDesc(desc);
	        post.setContent(content);
	        post.setPostedBy(postedBy);
	        post.setTags(tags);
	        post.setImage(imageName);
	        post.setPostedOn(LocalDateTime.now());

	        bservice.savePost(post);
	        
	        List<Blog> posts = bservice.getAllPosts();
	        if (posts.isEmpty()) {
	            System.out.println("No data found.");
	        }
	        
	        m.addAttribute("blogs", posts);
	        
		 return new ModelAndView("view"); 
	    }

	        @GetMapping("/posts/{id}")
	        public ModelAndView viewPost(@PathVariable("id") Long id, Model model) {
	        	System.out.println("into post id controll");
	            Blog blog = bservice.getPostById(id);
	            System.out.println("after post id controll");
	            if (blog == null) {
	            	System.out.println("into if");
	            	return new ModelAndView("index");  // Redirect to homepage or error page if post is not found
	            }
	            model.addAttribute("blog", blog);
	            return new ModelAndView("viewpost");  // Name of the HTML template
	        }
	        @GetMapping("/findAll")
	        public List<Blog> getCarousel() {
	        	 List<Blog> posts = bservice.getAllPosts();
	        	 return posts;
		    	 
		        
		     }
	        @Autowired
	        private contactService cService;

	        @PostMapping("/submitContact")
	        public ModelAndView submitContactForm(
	            @RequestParam("name") String name,
	            @RequestParam("email") String email,
	            @RequestParam("message") String message) {
	        	
	        	System.out.println("one");
	            Contact contact = new Contact();
	            contact.setName(name);
	            contact.setEmail(email);
	            contact.setMessage(message);
	            System.out.println("two");

	            cService.saveContact(contact);
	            System.out.println("three");

	            return new ModelAndView("contactSuccess");
	        }
	        @Autowired
	        private BlogRepo bRepo;

	        @PostMapping("/{id}/like")
	        public Blog likePost(@PathVariable Long id) {
	        	System.out.println("id1");
	            Blog blog = bRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found"));
	            
	            blog.setLikes(blog.getLikes() + 1);
	            System.out.println("id2");
	            return bRepo.save(blog);
	        }
	        
	        @GetMapping("/api/searchquery")
	        public List<Blog> searchPosts(@RequestParam String query) {
	        	System.out.print("inside search query");
	            return bservice.searchPosts(query);
	        }
	        @DeleteMapping("/posts/{id}")
	        public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
	            try {
	            	System.out.print("inside delete control");
	                bservice.deletePostById(id);
	                return ResponseEntity.ok().build();
	            } catch (ResponseStatusException e) {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	        }

	    

	 
	
}
