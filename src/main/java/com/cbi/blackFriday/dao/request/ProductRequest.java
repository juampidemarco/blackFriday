package com.cbi.blackFriday.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private Integer id;
    private Double price;
    private String name;
    private Double stock;
    private Double discount;
}
