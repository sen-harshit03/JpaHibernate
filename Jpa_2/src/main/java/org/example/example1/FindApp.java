package org.example.example1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.configuration.CustomerPersistenceUnitInfo;
import org.example.entity.Employee;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;
/*
Any changes made on the persistent object, are actually applied on proxy object. The proxy object is then compared with
the persistent object and changes are made in the persistent object, which get updated during commit;
 */
public class FindApp {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");

        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomerPersistenceUnitInfo(), props);
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Employee e1 = entityManager.find(Employee.class, 1); // Entity is loaded and persisted in the context
            System.out.println(e1);

            e1.setName("John Doe");  // These changes are made inside the proxy object.
            System.out.println(e1);

            entityManager.getTransaction().commit();  // during the commit,
        } finally {
            entityManager.close();
        }

    }
}
