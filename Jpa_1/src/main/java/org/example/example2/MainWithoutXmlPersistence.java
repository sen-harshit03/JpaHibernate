package org.example.example2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.configuration.CustomerPersistenceUnitInfo;
import org.example.entities.Product;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class MainWithoutXmlPersistence {
    public static void main(String[] args) {

        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomerPersistenceUnitInfo(), new HashMap());

        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Product p1 = new Product();
            p1.setId(2);
            p1.setName("Chocolate");
            entityManager.persist(p1);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

    }
}
