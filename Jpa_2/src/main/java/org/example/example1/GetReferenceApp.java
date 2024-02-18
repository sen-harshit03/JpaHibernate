package org.example.example1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.configuration.CustomerPersistenceUnitInfo;
import org.example.entity.Employee;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * find() -> 1 db call
 * remove() -> no call
 * commit ->  delete call
 *
 * getReference() -> no call
 * remove() -> no call
 * commit -> 1 call
 *
 */

public class GetReferenceApp {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");

        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomerPersistenceUnitInfo(), props);
        EntityManager entityManager = factory.createEntityManager();

//        try {
//            entityManager.getTransaction().begin();
//            System.out.println("1........");
//            Employee e1 = entityManager.find(Employee.class, 2); // Entity is loaded and persisted in the context
//            System.out.println("2........");
//            entityManager.remove(e1);
//            System.out.println("3.............");
//            entityManager.getTransaction().commit();  // during the commit,
//        } finally {
//            entityManager.close();
//        }


        try {
            entityManager.getTransaction().begin();
            System.out.println("1........");
            Employee e1 = entityManager.getReference(Employee.class, 2); // Proxy entity is stored in the context
            System.out.println("2........");
            entityManager.remove(e1);
            System.out.println("3.............");
            entityManager.getTransaction().commit();  // during the commit,
        } finally {
            entityManager.close();
        }

    }
}
