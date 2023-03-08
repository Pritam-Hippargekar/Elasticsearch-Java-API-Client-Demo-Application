package com.jentsch.skyer9;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Product {
    private String id;
    public String name;
    @JsonProperty("coordinates")
    public GeoLocation geoLocation;
    private String manufacturer;
    private String category;
    private Integer quantity;
    private BigDecimal price;
    private String description;
    public Double latitude;
    public Double longitude;
}
