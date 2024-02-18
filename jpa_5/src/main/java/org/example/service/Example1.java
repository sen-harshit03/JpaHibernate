package org.example.service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.configs.CustomPersistenceUnitInfo;
import org.example.entity.Product;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Example1 {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

//            TypedQuery<Product> query1 = em.createQuery("""
//                    SELECT p FROM Product p
//                    WHERE p.price > 50
//                    """, Product.class);
//            List<Product> products = query1.getResultList();
//            System.out.println(products);


//            TypedQuery<Product> query2 = em.createQuery("""
//                    SELECT p FROM Product p
//                    WHERE p.price > :price
//                    """, Product.class);
//            query2.setParameter("price", 50);
//            List<Product> productList = query2.getResultList();
//            System.out.println(productList);





//            String jpql = "SELECT p FROM Product p WHERE p.price > :price AND p.name LIKE :name";
//            TypedQuery<Product> query2 = em.createQuery(jpql, Product.class);
//            query2.setParameter("price", 60);
//            query2.setParameter("name", "%cho%");
//            List<Product> products = query2.getResultList();
//            System.out.println(products);


//            String jpql1 = "SELECT AVG(p.price) FROM Product p";
//            TypedQuery<Double> query = em.createQuery(jpql1, Double.class);
//            double avg = query.getSingleResult();
//            System.out.println(avg);


//            String jpql1 = "SELECT SUM(p.price) FROM Product p";
//            TypedQuery<BigDecimal> query = em.createQuery(jpql1, BigDecimal.class);
//            BigDecimal sum = query.getSingleResult();
//            System.out.println(sum);







            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
