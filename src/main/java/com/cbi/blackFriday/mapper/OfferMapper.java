package com.cbi.blackFriday.mapper;

import com.cbi.blackFriday.dao.request.OfferRequest;
import com.cbi.blackFriday.entities.Offer;
import com.cbi.blackFriday.entities.PaymentMethod;
import com.cbi.blackFriday.entities.Product;
import com.cbi.blackFriday.entities.User;
import com.cbi.blackFriday.exception.UserErrorException;
import com.cbi.blackFriday.repository.UserRepository;
import com.cbi.blackFriday.service.IOfferService;
import com.cbi.blackFriday.service.IProductService;
import com.cbi.blackFriday.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OfferMapper {

    @Autowired
    private IProductService productService;
    @Autowired
    private UserRepository userRepository;

public Offer toOffer(OfferRequest request){

    Offer offer = new Offer();
    offer.setAmount(request.getAmount());
    offer.setDate(request.getDate());
    offer.setCategory(request.getCategory());
    offer.setDuration(request.getDuration());
    offer.setPrice(request.getPrice());
    offer.setImportance(request.getImportance());
    offer.setProduct(productService.findById(request.getIdProd()));
    offer.setUser(userRepository.findById(request.getIdClient()).orElseThrow());
    offer.setUrgency(request.getUrgency());
    offer.setPaymentMethod(PaymentMethod.valueOf(request.getPaymentMethod()));

    return offer;
}
}
