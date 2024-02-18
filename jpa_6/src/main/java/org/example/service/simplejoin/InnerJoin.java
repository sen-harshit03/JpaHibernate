package org.example.service.simplejoin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.configs.CustomPersistenceUnitInfo;
import org.example.entity.Department;
import org.example.entity.Employee;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InnerJoin {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), props);

        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

//            List<Employee> employeeList = getEmployees();
//            List<Department> departmentList = getAllDepartment();
//
//            for(Employee employee : employeeList) {
//                em.persist(employee);
//            }
//
//            for(Department department : departmentList) {
//                em.persist(department);
//            }

            String jpql = """
                    SELECT e, d
                    FROM Employee e
                    INNER JOIN Department d
                    ON e.deptId = d.deptId
                    """;
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            List<Object[]> resultList = query.getResultList();
            System.out.println(resultList.size());

            resultList.forEach(o -> System.out.println(o[0] + " " + o[1]));



            em.getTransaction().commit();
        } finally {
            em.close();
        }


    }


    private static List<Employee> getEmployees() {
        Employee e1 = Employee.builder()
                .empId("E1")
                .empName("Manoj")
                .empSalary(40000L)
                .managerId("M1")
                .deptId("D1")
                .build();

        Employee e2 = Employee.builder()
                .empId("E2")
                .empName("Srinivas")
                .empSalary(60000L)
                .managerId("M1")
                .deptId("D1")
                .build();
        Employee e3 = Employee.builder()
                .empId("E3")
                .empName("Rahul")
                .empSalary(45000L)
                .managerId("M2")
                .deptId("D2")
                .build();

        Employee e4 = Employee.builder()
                .empId("E4")
                .empName("Vikas")
                .empSalary(55000L)
                .managerId("M2")
                .deptId("D2")
                .build();

        Employee e5 = Employee.builder()
                .empId("E5")
                .empName("Riya")
                .empSalary(35000L)
                .managerId("M3")
                .deptId("D10")
                .build();

        Employee e6 = Employee.builder()
                .empId("E6")
                .empName("Sneha")
                .empSalary(65000L)
                .managerId("M3")
                .deptId("D10")
                .build();

        return List.of(e1, e2, e3, e4, e5, e6);
    }

    private static List<Department> getAllDepartment() {
        Department d1 = new Department("D1", "IT");
        Department d2 = new Department("D2", "HR");
        Department d3 = new Department("D3", "Sales");
        Department d4 = new Department("D4", "Admin");

        return List.of(d1, d2, d3, d4);
    }
}
