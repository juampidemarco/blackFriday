package com.cbi.blackFriday.service;


import com.cbi.blackFriday.entities.Offer;
import com.cbi.blackFriday.entities.Product;
import com.cbi.blackFriday.entities.User;

import java.util.List;
import java.util.Optional;

public interface IOfferService {
    Offer save (Offer offer);
    Optional<Offer> getOfferById(Integer id);
    List<Offer> getOffersByClient(Integer id);
    boolean validate (Offer offer);
    Optional<User> getUser(Integer id);
    Optional<Product> getProduct(Integer id);
}
