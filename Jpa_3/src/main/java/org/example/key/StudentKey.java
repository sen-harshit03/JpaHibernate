package org.example.key;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter @Getter
public class StudentKey {

    private String alias;
    private String name;
}
