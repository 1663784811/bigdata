package com.cyyaw.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;

    // Getters and Setters
}
