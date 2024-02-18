package org.example.example1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import org.example.configs.CustomPersistenceUnitInfo;
import org.example.entity1.Passport;
import org.example.entity1.Person;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Demo1 {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");
        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);

        EntityManager em = factory.createEntityManager();

        try {
            em.getTransaction().begin();
            Passport passport = new Passport();
            passport.setNumber("IND123");

            Person person = new Person();
            person.setName("Harshit");
            person.setPassport(passport);


            em.persist(person);
//            em.persist(passport);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
