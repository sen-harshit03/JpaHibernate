package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.configs.CustomPersistenceUnitInfo;
import org.example.entity.Customer;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

/**
 *          Criteria Query: Will be used when we are not sure about the query beforehand. or query might vary with params
 *
 *          JPQL: Select, Delete and updates (No insert - Insert is handled by ORM)
 *          Criteria Query: Select
 */

public class Example1 {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();  // builder
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);  // criteria query

            Root<Customer> customerRoot = criteriaQuery.from(Customer.class);

            criteriaQuery.select(customerRoot); // SELECT c FROM Customer c

            TypedQuery<Customer> query = em.createQuery(criteriaQuery);

            query.getResultList().forEach(System.out::println);









            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
