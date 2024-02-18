package org.example.example2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.configs.CustomPersistenceUnitInfo;
import org.example.entity1.Passport;
import org.example.entity1.Person;
import org.example.entity2.Comment;
import org.example.entity2.Post;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example2 {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");
        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);

        EntityManager em = factory.createEntityManager();

        try {
            em.getTransaction().begin();

            Comment comment1 = new Comment();
            comment1.setCommentContent("Wow beautiful :)");

            Post post = new Post();
            post.setPostContent("A goth girl");

            post.setComments(List.of(comment1));
            comment1.setPost(post);

            em.persist(post);
            em.persist(comment1);


            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
