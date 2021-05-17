package com.skrylov.springsecuritypractice.service;

import com.skrylov.springsecuritypractice.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
