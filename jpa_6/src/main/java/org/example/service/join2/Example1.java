package org.example.service.join2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.configs.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example1 {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");

        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);

        EntityManager em = factory.createEntityManager();
        try{
            em.getTransaction().begin();

//            String jpql = """
//                    SELECT s, e
//                    FROM Student s
//                    INNER JOIN s.enrollments e
//                    """;

//            String jpql = """
//                    SELECT s, e
//                    FROM Student s
//                    JOIN s.enrollments e
//                    """;
//            String jpql = """
//                    SELECT s, e
//                    FROM Student s, Enrollment e
//                    WHERE s.id = e.student.id
//                    """;

            String jpql = """
                    SELECT s, e
                    FROM Student s, Enrollment e
                    WHERE s = e.student
                    """;



            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            List<Object[]> res = query.getResultList();

            res.forEach(o -> System.out.println(o[0] + " " + o[1]));


            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
