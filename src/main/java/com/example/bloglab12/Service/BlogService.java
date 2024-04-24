package com.example.bloglab12.Service;

import com.example.bloglab12.Api.ApiException;
import com.example.bloglab12.Model.Blog;
import com.example.bloglab12.Model.User;
import com.example.bloglab12.Repository.AuthRepository;
import com.example.bloglab12.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public void addBlog(String username, Blog blog){
        User user = authRepository.findUserByUsername(username);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    public void updateBlog(Integer userId, Integer blogId, Blog blog){
        Blog b = blogRepository.findBlogById(blogId);
        if(b == null){
            throw new ApiException("blog not found");
        }else if(!b.getUser().getId().equals(userId)){
            throw new ApiException("blog does not belong to user");
        }
        b.setTitle(blog.getTitle());
        b.setBody(blog.getBody());
        blogRepository.save(b);
    }

    public void deleteBlog(Integer userId, Integer blogId){
        Blog blog = blogRepository.findBlogById(blogId);
        if(blog == null){
            throw new ApiException("blog not found");
        }else if(!blog.getUser().getId().equals(userId)){
            throw new ApiException("blog does not belong to user");
        }
        blogRepository.delete(blog);
    }

    public Blog getBlogById(Integer blogId){
        Blog blog = blogRepository.findBlogById(blogId);
        if(blog == null){
            throw new ApiException("blog not found");
        }
        return blog;
    }

    public Blog getBlogByTitle(String title){
        Blog blog = blogRepository.findBlogByTitle(title);
        if(blog == null){
            throw new ApiException("blog not found");
        }
        return blog;
    }

}
