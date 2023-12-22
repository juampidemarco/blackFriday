package com.cbi.blackFriday.service.impl;

import com.cbi.blackFriday.entities.Offer;
import com.cbi.blackFriday.entities.PaymentMethod;
import com.cbi.blackFriday.exception.OfferNotFoundException;
import com.cbi.blackFriday.exception.OfferValidationException;
import com.cbi.blackFriday.repository.OfferRepository;
import com.cbi.blackFriday.service.IOfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OfferServiceImpl implements IOfferService {

    @Autowired
    private OfferRepository offerRepository;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Override
    public Offer save(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public List<Offer> getOfferByClient(Integer id) {
        log.debug("enter getOfferByClient offer service");

        List<Offer> offers = offerRepository.findAll().stream().filter(o -> o.getIdClient().equals(id)).toList();

        if (offers.isEmpty()){
            log.error("offer not found for client "+ id);
            throw new OfferNotFoundException("offer not found");
        }

        return offers;
    }

    @Override
    public boolean validate(Offer offer) {
        log.debug("enter validate offer service");

        if (offer.getImportance() > 5 && offer.getImportance() < 0){
            throw new OfferValidationException("importance can not be > 5");
        }

        if (offer.getUrgency() > 3 && offer.getUrgency() < 0){
            throw new OfferValidationException("urgency can not be > 3");
        }
        if (offer.getPaymentMethod().equals(PaymentMethod.CASH) || offer.getPaymentMethod().equals(PaymentMethod.DEBIT)
        || offer.getPaymentMethod().equals(PaymentMethod.CREDIT)){
            throw new OfferValidationException("payment method invalid");
        }

        return true;
    }
}
