package com.example.service;

import java.util.*;

import com.example.exception.UserNotFoundException;
import com.example.model.User;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User save(User user) {
        repo.save(user);
        return user;
    }

    public Optional<User> findOne(int id) {
        return repo.findById(id);
    }

    public boolean exists(int id) {
        return repo.existsById(id);
    }

    public void deleteById(int id) {
         repo.deleteById(id);
    }

}
