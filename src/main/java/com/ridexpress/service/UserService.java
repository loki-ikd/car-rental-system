package com.ridexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ridexpress.entity.User;
import com.ridexpress.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User registerUser(User user) {
        return repo.save(user);
    }

    public User loginUser(String email, String password) {
        User existingUser = repo.findByEmail(email);
        if (existingUser != null && existingUser.getPassword().equals(password)) {
            return existingUser;
        }
        return null;
    }
}
