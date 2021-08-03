package com.example.todowebapp.service;


import com.example.todowebapp.model.User;

import java.util.Optional;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    void updatePassword(String password, Long userId);

    public Optional<User> findUserByResetToken(String resetToken);

    public boolean isValidUser(String username, String password);
}