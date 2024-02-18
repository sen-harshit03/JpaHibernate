package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "employees")
@Getter @Setter @ToString @NoArgsConstructor
public class Employee {
    @Id
    private int id;

    @Column(name = "name", length = 45)
    private String name;

    private String address;
}
