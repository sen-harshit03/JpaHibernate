package org.example.example1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.configuration.CustomerPersistenceUnitInfo;
import org.example.entity.Employee;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class PersistApp {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");

        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomerPersistenceUnitInfo(), props);
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Employee e1 = new Employee();
            e1.setId(2);
            e1.setAddress("New York");
            e1.setName("John");  // entity at transient state

            Employee e2 = new Employee();
            e2.setId(3);
            e2.setAddress("New York");
            e2.setName("Arya");  // entity at transient state

            System.out.println("Before Persist");
            entityManager.persist(e1);   // moved to persisted state, added to the context
            entityManager.persist(e2);   // moved to persisted state, added to the context
            System.out.println("after Persist");

            entityManager.getTransaction().commit();  // commit
        } finally {
            entityManager.close();
        }

    }
}
