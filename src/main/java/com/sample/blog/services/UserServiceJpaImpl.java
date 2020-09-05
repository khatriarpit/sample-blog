package com.sample.blog.services;

import com.sample.blog.models.User;
import com.sample.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceJpaImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> findAll() {
        return this.userRepo.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = this.userRepo.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public User create(User user) {
        return this.userRepo.save(user);
    }

    @Override
    public boolean authenticate(String username, String password) {
        return false;
    }

    @Override
    public boolean hasUser(String username) {
//        return userRepo.;
        return false;
    }

    @Override
    public void create(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(password);
        userRepo.save(user);
    }

    @Override
    public User edit(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepo.deleteById(id);
    }
}
