package com.example.bloglab12.Service;

import com.example.bloglab12.Model.Blog;
import com.example.bloglab12.Model.User;
import com.example.bloglab12.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void register(User user){
        user.setRole("USER");
        authRepository.save(user);
    }

    public List<User> getAllUser(){
       return authRepository.findAll();
    }
    public void updateUser(String username, User user){
        User u = authRepository.findUserByUsername(username);
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        authRepository.save(u);
    }

    public void deleteUser(String username){
        User user = authRepository.findUserByUsername(username);
        authRepository.delete(user);
    }

    public Set<Blog> getAllBlogsByUser(String username){
        User user = authRepository.findUserByUsername(username);
        return user.getBlogs();
    }

}
