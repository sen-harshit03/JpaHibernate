package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String enrollName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @ManyToOne
    private Student student;

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", enrollName='" + enrollName + '\'' +
//                ", course=" + course.getName() +
//                ", student=" + student.getName() +
                '}';
    }
}
