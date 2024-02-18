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
public class DetachApp {
    public static void main(String[] args) {

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");


        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomerPersistenceUnitInfo(), props);
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Employee e1 = new Employee();
            e1.setId(3);
            e1.setAddress("Bangalore");
            e1.setName("Akash");  // entity at transient state

            System.out.println("Before Persist");
            entityManager.persist(e1);   // moved to persisted state, added to the context
            System.out.println("after Persist");

            System.out.println("Before Remove");
            entityManager.detach(e1);   // moved to persisted state, added to the context
            System.out.println("after Remove");
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
