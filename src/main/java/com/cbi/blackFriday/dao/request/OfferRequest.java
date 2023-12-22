package com.cbi.blackFriday.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferRequest {
    private Integer id;
    private Integer idProd;
    private Integer idClient;
    private Double price;
    private String date;
    private Integer amount;
    private Integer importance;
    private Integer duration;
    private Integer urgency;
    private String category;
    private String paymentMethod;

}
