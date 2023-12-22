package com.cbi.blackFriday.service;


import com.cbi.blackFriday.entities.Offer;

import java.util.List;

public interface IOfferService {
    Offer save (Offer offer);

    List<Offer> getOfferByClient(Integer id);

    boolean validate (Offer offer);
}
