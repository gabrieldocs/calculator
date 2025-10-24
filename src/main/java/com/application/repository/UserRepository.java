package com.application.repository;

import java.util.List;

import com.application.model.User;

public interface UserRepository {
    User save(User user);
    User findById(Long id);
    List<User> findAll();
    void delete(Long id);
}
