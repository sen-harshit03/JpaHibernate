package org.example.key;

import jakarta.persistence.Id;

import java.io.Serializable;


public class ProductKey implements Serializable {
    private String modelNumber;
    private String year;
}
