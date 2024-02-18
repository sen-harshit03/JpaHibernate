package org.example.example3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.configs.CustomPersistenceUnitInfo;
import org.example.entity3.Policy;
import org.example.entity3.User;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example3 {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");
        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);

        EntityManager em = factory.createEntityManager();

        try {
            em.getTransaction().begin();

            Policy policy1 = new Policy();
            policy1.setName("Policy 1");

            User user1 = new User();
            user1.setName("User1");
            user1.setPolicies(List.of(policy1));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
