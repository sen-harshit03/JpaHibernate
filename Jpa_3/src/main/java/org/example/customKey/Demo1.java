package org.example.customKey;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.CustomPersistenceUnitInfo;
import org.example.entity.Employee;
import org.example.entity.Product;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Demo1 {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");
        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);

        EntityManager em = factory.createEntityManager();

        try {
            em.getTransaction().begin();
            Product p1 = new Product();
            p1.setModelNumber("ABC");
            p1.setYear("2017");
            p1.setName("Iphone");

            em.persist(p1);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
