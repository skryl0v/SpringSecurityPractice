package com.skrylov.springsecuritypractice.dao;

import com.skrylov.springsecuritypractice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
