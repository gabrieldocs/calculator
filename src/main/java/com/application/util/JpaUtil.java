package com.application.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("db-far");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static void closeFactory() {
        if(FACTORY != null && FACTORY.isOpen()) {
            FACTORY.close();
            System.out.println("JPA Factory shut down.");
        }
    }
}
