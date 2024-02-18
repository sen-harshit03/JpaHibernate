package org.example.service;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.configs.CustomPersistenceUnitInfo;
import org.example.entity.Author;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Every Collection associated with an entity is fetched LAZYily. If we fetch the entity, we will not fetch the Collection
 *  But when we are trying to access or work on that collection hibernate will make another queries to fetch the associated
 *  Collections.
 *  In this way we are making n+1 query:
 *  1: query to fetch the required entity
 *  n: queries to fetch all the associated entities (Collection.size())
 *
 *
 *          If we look the situation, we can this entity-relation is basically a graph
 *                  has A        has A
 *          Author <-----> Book <------> BookList
 *
 *
 *          It is undirected graph:
 *          Node = Entity
 *          Edge = Relationship (has A)
 *
 *          Problem Statement : Fetch Author, then fetch its corresponding BookList too.
 *          Sol1: Making fetchType = FetchType.EAGER
 *                Cons:
 *                No reduction in queries
 *                Let say for one query I want the BookList to be fetched eagerly while for other I want lazily
 *
 *          Sol2:
 *              EntityGraph
 *              Here we specify through the graph what we want to fetch eagerly
 *              - Author
 *              - Author --- Book
 *
 *
 *
 *
 */

public class EntityGraphDemo {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            String jpql = """
                    SELECT a FROM Author a
                    """;

            EntityGraph<?> authorGraph = em.createEntityGraph(Author.class);   // Author -----> Book
            authorGraph.addAttributeNodes("bookList");


            TypedQuery<Author> query = em.createQuery(jpql, Author.class)
                            .setHint("jakarta.persistence.loadgraph", authorGraph);   // Only 1 query

            List<Author> authorList = query.getResultList();

            authorList.forEach(a -> System.out.println(a));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}