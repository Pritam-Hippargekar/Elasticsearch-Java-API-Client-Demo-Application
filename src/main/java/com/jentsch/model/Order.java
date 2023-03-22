package com.jentsch.model;

import lombok.Data;

@Data
@Document(indexName = "order_test")
@Setting(replicas = 0)
public class Order {

    @Id
    private String id;
}
