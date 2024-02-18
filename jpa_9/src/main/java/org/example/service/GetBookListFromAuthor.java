package org.example.service;

import jakarta.persistence.*;
import org.example.configs.CustomPersistenceUnitInfo;
import org.example.entity.Author;
import org.example.entity.Book;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetBookListFromAuthor {
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

            EntityGraph<?> authorGraph = em.createEntityGraph(Author.class);
            Subgraph<?> bookGraph = authorGraph.addSubgraph("bookList");
            bookGraph.addAttributeNodes("bookShopList");



            TypedQuery<Author> query = em.createQuery(jpql, Author.class)
                    .setHint("jakarta.persistence.loadgraph", authorGraph);   // Only 1 query

            List<Author> authorList = query.getResultList();

            authorList.forEach(
                    a -> {
                        Set<Book> bookList = a.getBookList();
                        bookList.forEach(
                                book -> System.out.println(book.getBookShopList())
                        );
                    }
            );

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
