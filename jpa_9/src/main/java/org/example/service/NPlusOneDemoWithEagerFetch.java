package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.configs.CustomPersistenceUnitInfo;
import org.example.entity.Author;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NPlusOneDemoWithEagerFetch {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            String jpql = "SELECT a FROM Author a";   // 1 query to fetch the 5 authors
            TypedQuery<Author> query = em.createQuery(jpql, Author.class);

            List<Author> authorList = query.getResultList(); // 5  queries to fetch the bookList of each author

            authorList.forEach(
                    a -> System.out.println(a.getBookList())
            );

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
