package com.example.bloglab12.Controller;

import com.example.bloglab12.Api.ApiResponse;
import com.example.bloglab12.Model.User;
import com.example.bloglab12.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        authService.register(user);
        logger.info("register");
        return ResponseEntity.ok(new ApiResponse("registered"));
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsers(){
        logger.info("all user requested");
        return ResponseEntity.ok(authService.getAllUser());
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal User user, User updatedUser){
        authService.updateUser(user.getUsername(), updatedUser);
        logger.info("user updated");
        return ResponseEntity.ok(new ApiResponse("user update"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal User user){
        authService.deleteUser(user.getUsername());
        logger.info("user deleted");
        return ResponseEntity.ok(new ApiResponse("user deleted"));
    }

    @GetMapping("/user/blogs")
    public ResponseEntity getAllBlogsByUser(@AuthenticationPrincipal User user){
        authService.getAllBlogsByUser(user.getUsername());
        logger.info("all blogs by user");
        return ResponseEntity.ok(new ApiResponse("all blogs by user"));
    }
}
