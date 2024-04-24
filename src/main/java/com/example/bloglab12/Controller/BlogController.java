package com.example.bloglab12.Controller;

import com.example.bloglab12.Api.ApiResponse;
import com.example.bloglab12.Model.Blog;
import com.example.bloglab12.Model.User;
import com.example.bloglab12.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    Logger logger = LoggerFactory.getLogger(BlogController.class);



    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog){
        blogService.addBlog(user.getUsername(), blog);
        logger.info("blog added");
        return ResponseEntity.ok(new ApiResponse("blog added"));
    }

    @GetMapping("/blogs")
    public ResponseEntity getAllBlogs(){
        logger.info("all blogs requested");
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @PutMapping("/update/{blog_id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @PathVariable Integer blog_id, @RequestBody @Valid Blog blog){
        blogService.updateBlog(user.getId(), blog_id, blog);
        logger.info("blog updated");
        return ResponseEntity.ok(new ApiResponse("blog updated"));
    }

    @DeleteMapping("/delete/{blog_id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer blog_id){
        blogService.deleteBlog(user.getId(),blog_id);
        logger.info("blog deleted");
        return ResponseEntity.ok(new ApiResponse("blog deleted"));
    }


    @GetMapping("/get-by-id/{blog_id}")
    public ResponseEntity getBlogById(@PathVariable Integer blog_id){
        logger.info("blog by id requested");
        return ResponseEntity.ok(blogService.getBlogById(blog_id));
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title){
        logger.info("blog by title requested");
        return ResponseEntity.ok(blogService.getBlogByTitle(title));
    }


}
