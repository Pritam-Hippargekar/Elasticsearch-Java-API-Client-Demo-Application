package com.jentsch.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
public class Category {
    private Long categoryId;
    private String categoryName;
    private Set<Product> products;
}
