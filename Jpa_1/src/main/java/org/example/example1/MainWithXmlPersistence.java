package org.example.example1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Product;


public class MainWithXmlPersistence {
    public static void main(String[] args) {

        // Factory will give entitymanager
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("demo-persistent-unit");
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Product p1 = new Product();
            p1.setId(1);
            p1.setName("Beer");
            entityManager.persist(p1);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
