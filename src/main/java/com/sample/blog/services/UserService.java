package com.sample.blog.services;

import com.sample.blog.models.User;

import java.util.List;

public interface UserService {
    boolean authenticate(String username, String password);
    boolean hasUser(String username);
    void create(String username, String password);
    User create(User user);
    List<User> findAll();
    User findById(Long id);
    User edit(User user);
    void deleteById(Long id);
}
