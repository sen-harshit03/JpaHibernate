package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Employee {
    @Id
    private String empId;
    private String empName;
    private Long empSalary;

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", empSalary=" + empSalary +
                ", deptId='" + deptId + '\'' +
                ", managerId='" + managerId + '\'' +
                '}';
    }

    private String deptId;
    private String managerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(empId, employee.empId) && Objects.equals(empName, employee.empName) && Objects.equals(empSalary, employee.empSalary) && Objects.equals(deptId, employee.deptId) && Objects.equals(managerId, employee.managerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, empName, empSalary, deptId, managerId);
    }

}
