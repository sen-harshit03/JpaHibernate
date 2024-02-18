package org.example.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.example.key.StudentKey;

@Getter @Setter
@Entity
public class Student {
    @EmbeddedId
    private StudentKey id;
    private int marks;
}
