package org.example.example1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.configuration.CustomerPersistenceUnitInfo;
import org.example.entity.Employee;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Employee e1 = entityManager.find(Employee.class, 1);  -> Hibernate: select e1_0.id,e1_0.address,e1_0.name from employees e1_0 where e1_0.id=?
 * entityManager.remove(e1) -> No hibernate query
 * entityManager.getTransaction().commit(); -> delete from employees where id=?
 */
public class RemoveApp {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");

        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomerPersistenceUnitInfo(), props);
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            System.out.println("Before find");
            Employee e1 = entityManager.find(Employee.class, 2);  // One select query
            System.out.println("After find");

            System.out.println("Before find again");
            entityManager.find(Employee.class, 2);   // No select query
            System.out.println("After find again");

            System.out.println("Before removing");
            entityManager.remove(e1);  // removing the persistent object
            System.out.println("After removing");

            System.out.println(entityManager.contains(e1));  // False

            entityManager.getTransaction().commit();  // One delete query
        } finally {
            entityManager.close();
        }

    }
}
