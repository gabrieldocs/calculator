package com.application.service;

import java.util.List;

import com.application.model.User;
import com.application.repository.UserRepositoryImpl;
import com.application.util.JpaUtil;

import jakarta.persistence.EntityManager;

public class UserService {

    UserRepositoryImpl repository = null;
    EntityManager em = null;
    
    public UserService() {
    }
    
    public void create(String name, String email) {
        em = JpaUtil.getEntityManager();
        repository = new UserRepositoryImpl(em);
        try {

            em.getTransaction().begin();
            User user = new User(name, email);
            repository.save(user);
            System.out.println("User saved with id " + user.getId());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to register user ", e);
        } finally {
            em.close();
        }
    }

    public void findAll() {
        em = JpaUtil.getEntityManager();
        repository = new UserRepositoryImpl(em);

        try {
            em.getTransaction().begin();
            List<User> users = repository.findAll();
            if(users.isEmpty()) {
                System.out.println("No users found!");
            }
            users.forEach(u -> System.out.println(u.getId() +" " + u.getName() + " " + u.getEmail()));
        } finally {
            em.close();
        }
    }
}
