package org.example.example1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.configuration.CustomerPersistenceUnitInfo;
import org.example.entity.Employee;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Referesh(obj) -> Makes a select call to the DB and fetches its state from the DB.
 * Used to do some undo before the commit.
 *
 */
public class RefreshApp {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");

        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomerPersistenceUnitInfo(), props);
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Employee e1 = entityManager.find(Employee.class, 3); // 1 select call
            System.out.println(e1);

            e1.setName("John Doe");    // name: John Doe
            System.out.println("Before refresh : " + e1);
            entityManager.refresh(e1);  // 1 select call
            System.out.println("After refresh refresh : " +e1);

            entityManager.getTransaction().commit();  //  No call to DB
        } finally {
            entityManager.close();
        }

    }
}
