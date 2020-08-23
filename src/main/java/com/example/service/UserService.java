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

    private static List<User> users = new ArrayList<>();
    private final UserRepository repo;
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
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
