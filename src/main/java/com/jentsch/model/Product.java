package com.jentsch.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
public class Product {
    private Long productId;
    private String productName;
    private BigDecimal price;
    private String description;
    private Integer stocks;
    private Boolean active;
    private GeoLocation location;
    private Date createdAt;
    private Date updatedAt;
}
