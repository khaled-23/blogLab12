package com.example.bloglab12.Repository;

import com.example.bloglab12.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AuthRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer userId);
    User findUserByUsername(String username);


}
