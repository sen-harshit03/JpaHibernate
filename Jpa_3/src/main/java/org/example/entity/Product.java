package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.key.ProductKey;

import java.util.Objects;

/**
 * Always override the equals and hash code method in case of composite key
 */
@Entity
@Setter @Getter
@IdClass(ProductKey.class)
@EqualsAndHashCode
public class Product {
    @Id
    private String modelNumber;
    @Id
    private String year;

    private String name;

}
