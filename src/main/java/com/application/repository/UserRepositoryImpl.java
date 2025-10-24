package com.application.repository;

import java.util.List;

import com.application.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User user) {
        User merge = entityManager.merge(user);
        return merge;
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        if(user != null) {
            entityManager.remove(user);
        } else {
            throw new NoResultException("User with ID " + id + " Not found!");
        }
    }
    
}
