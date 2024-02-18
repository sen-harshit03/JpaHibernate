package org.example.example1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.configuration.CustomerPersistenceUnitInfo;
import org.example.entity.Employee;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

/**  e1 is present in the database and there is change in the object
 * entityManager.merge(e1) -> Hibernate: select e1_0.id,e1_0.address,e1_0.name from employees e1_0 where e1_0.id=?
 * entityManager.getTransaction().commit(); -> Hibernate: update employees set address=?,name=? where id=?
 *
 * e1 is present in the database and there is NO change in the object
 * entityManager.merge(e1) -> Hibernate: select e1_0.id,e1_0.address,e1_0.name from employees e1_0 where e1_0.id=?
 * entityManager.getTransaction().commit(); -> No hibernate calls
 *
 * e1 is not present in the database
 * entityManager.merge(e1) -> Hibernate: select e1_0.id,e1_0.address,e1_0.name from employees e1_0 where e1_0.id=?
 * entityManager.getTransaction().commit(); -> Hibernate: insert into employees (address,name,id) values (?,?,?)
 */
public class MergeApp {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");

        EntityManagerFactory factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomerPersistenceUnitInfo(), props);
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Employee e1 = new Employee();
            e1.setId(2);
            e1.setAddress("New York");
            e1.setName("John");  // entity at transient state

            System.out.println("Before");
            entityManager.merge(e1);   // moved to persisted state, added to the context
            System.out.println("after");

            entityManager.getTransaction().commit();  // commit
        } finally {
            entityManager.close();
        }

    }
}
