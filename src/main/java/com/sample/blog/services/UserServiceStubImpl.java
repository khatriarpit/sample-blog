package com.sample.blog.services;

import com.sample.blog.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceStubImpl implements UserService{

    @Override
    public boolean authenticate(String username, String password) {
        // Provide a sample password check: username == password
        return Objects.equals(username, password);
    }

    @Override
    public boolean hasUser(String username) {
        return true;
    }

    @Override
    public void create(String username, String password) {
        return;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<User>();
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User edit(User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
