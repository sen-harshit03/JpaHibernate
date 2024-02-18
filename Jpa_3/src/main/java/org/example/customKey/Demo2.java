package org.example.customKey;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.CustomPersistenceUnitInfo;
import org.example.entity.Product;
import org.example.entity.Student;
import org.example.key.StudentKey;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Demo2 {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");
        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);

        EntityManager em = factory.createEntityManager();

        try {
            em.getTransaction().begin();
            StudentKey studentKey = new StudentKey();
            studentKey.setName("harshit");
            studentKey.setAlias("senharshit");

            Student student = new Student();
            student.setId(studentKey);
            student.setMarks(45);


            em.persist(student);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
