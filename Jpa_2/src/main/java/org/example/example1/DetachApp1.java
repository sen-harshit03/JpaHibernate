package org.example.example1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.configuration.CustomerPersistenceUnitInfo;
import org.example.entity.Employee;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

/**
 *  entityManager.persist(e1);  -> No call
 *  entityManager.remove(e1); -> No call
 *  entityManager.getTransaction().commit(); -> 2 calls (insert + delete)
 *                                          Hibernate: insert into employees (address,name,id) values (?,?,?)
 *                                          Hibernate: delete from employees where id=?
 *
 * entityManager.persist(e1);  -> No call
 * entityManager.detach(e1); -> No call
 * entityManager.getTransaction().commit(); -> Insert query, but it will throw an exception
 * JPA specification states that you ‘must use the flush method before the detach method,’
 */
public class DetachApp1 {
    public static void main(String[] args) {

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");


        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomerPersistenceUnitInfo(), props);
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            System.out.println("Before find");
            Employee e1 = entityManager.find(Employee.class, 2);
            System.out.println("after Find");

            e1.setName("Peter");

            System.out.println("Before Detach");
            entityManager.detach(e1);   // moved to persisted state, added to the context
            System.out.println("after Detach");
            System.out.println(entityManager.contains(e1));

            entityManager.getTransaction().commit();  // commit
        } catch (Exception e) {

            e.printStackTrace();
            entityManager.getTransaction().rollback();

        } finally {
            entityManager.close();
        }

    }
}
