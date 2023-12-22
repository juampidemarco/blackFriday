package com.cbi.blackFriday.service.impl;

import com.cbi.blackFriday.entities.Offer;
import com.cbi.blackFriday.entities.PaymentMethod;
import com.cbi.blackFriday.entities.Product;
import com.cbi.blackFriday.entities.User;
import com.cbi.blackFriday.exception.OfferException;
import com.cbi.blackFriday.exception.OfferNotFoundException;
import com.cbi.blackFriday.exception.OfferValidationException;
import com.cbi.blackFriday.repository.OfferRepository;
import com.cbi.blackFriday.repository.ProductRepository;
import com.cbi.blackFriday.repository.UserRepository;
import com.cbi.blackFriday.service.IOfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class OfferServiceImpl implements IOfferService {

    @Autowired
    private OfferRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Override
    public Offer save(Offer offer) {
        log.debug("enter save offer service");
        Offer savedOffer;

        try {
            savedOffer = repository.save(offer);
        } catch (Exception e){
            log.error("error saving offer: "+ e.getMessage());
            throw new OfferException("error save offer");
        }

        return savedOffer;
    }

    @Override
    public Optional<Offer> getOfferById(Integer id) {
        log.debug("enter getOfferById offer service");

        Optional<Offer> offer = repository.findById(id);

        if (offer.isEmpty()){
            log.error("offer not found for id "+ id);
            throw new OfferNotFoundException("offer not found");
        }

        return offer;
    }


    @Override
    public List<Offer> getOffersByClient(Integer id) {
        log.debug("enter getOfferByClient offer service");

        List<Offer> offers = repository
                .findAll()
                .stream()
                .filter(o -> o.getUser().getId().equals(id))
                .sorted(Comparator.comparingInt(Offer::getImportance))
                .toList();

        if (offers.isEmpty()){
            log.error("offer not found for client "+ id);
            throw new OfferNotFoundException("offer not found");
        }

        return offers;
    }

    @Override
    public boolean validate(Offer offer) {
        log.debug("enter validate offer service");

        if (offer.getImportance() > 5 || offer.getImportance() < 0){
            log.error("importance can not be > 5. Actual value: "+offer.getImportance());
            throw new OfferValidationException("importance can not be > 5");
        }

        if (offer.getUrgency() > 3 || offer.getUrgency() < 0){
            log.error("urgency can not be > 3. Actual value: "+offer.getUrgency());
            throw new OfferValidationException("urgency can not be > 3");
        }
        if (!offer.getPaymentMethod().equals(PaymentMethod.CASH) && !offer.getPaymentMethod().equals(PaymentMethod.DEBIT)
        && !offer.getPaymentMethod().equals(PaymentMethod.CREDIT)){
            log.error("invalid payment method "+ offer.getPaymentMethod());
            throw new OfferValidationException("invalid payment method");
        }

        return true;
    }

    @Override
    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public  Optional<Product> getProduct(Integer id) {
        return productRepository.findById(id);
    }
}
