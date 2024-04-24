package com.example.bloglab12.Repository;

import com.example.bloglab12.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Blog findBlogById(Integer blogId);
    Blog findBlogByTitle(String title);
}
