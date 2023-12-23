package com.cbi.blackFriday.controller;

import com.cbi.blackFriday.dao.request.OfferRequest;
import com.cbi.blackFriday.entities.Offer;
import com.cbi.blackFriday.entities.PaymentMethod;
import com.cbi.blackFriday.exception.ProductNotFoundException;
import com.cbi.blackFriday.exception.UserErrorException;
import com.cbi.blackFriday.service.IKafkaPublisher;
import com.cbi.blackFriday.service.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offer")
public class OfferController {

    @Autowired
    private IOfferService service;
    @Autowired
    private IKafkaPublisher publisher;

    @PostMapping("/create")
    public ResponseEntity<Offer> create(@RequestBody OfferRequest request) {

        Offer offer = new Offer();
        offer.setAmount(request.getAmount());
        offer.setDate(request.getDate());
        offer.setCategory(request.getCategory());
        offer.setDuration(request.getDuration());
        offer.setPrice(request.getPrice());
        offer.setImportance(request.getImportance());
        offer.setProduct(service.getProduct(request.getIdProd()).orElseThrow(() -> new ProductNotFoundException("product not found")));
        offer.setUser(service.getUser(request.getIdClient()).orElseThrow(() -> new UserErrorException("user not found")));
        offer.setUrgency(request.getUrgency());
        offer.setPaymentMethod(PaymentMethod.valueOf(request.getPaymentMethod()));

        service.validate(offer);

        service.save(offer);

        return ResponseEntity.ok(offer);
    }


    @GetMapping("/show/{id}")
    public ResponseEntity<List<Offer>> show(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getOffersByClient(Math.toIntExact(id)));
    }

    @PostMapping("/publish")
    public ResponseEntity<String> notify(@RequestBody OfferRequest request) {
        Offer offer = new Offer();
        offer.setAmount(request.getAmount());
        offer.setDate(request.getDate());
        offer.setCategory(request.getCategory());
        offer.setDuration(request.getDuration());
        offer.setPrice(request.getPrice());
        offer.setImportance(request.getImportance());
        offer.setProduct(service.getProduct(request.getIdProd()).orElseThrow(() -> new ProductNotFoundException("product not found")));
        offer.setUser(service.getUser(request.getIdClient()).orElseThrow(() -> new UserErrorException("user not found")));
        offer.setUrgency(request.getUrgency());
        offer.setPaymentMethod(PaymentMethod.valueOf(request.getPaymentMethod()));

        service.validate(offer);
        publisher.sendEventsToTopic(offer);

        return ResponseEntity.ok("message published successfully ..");

    }

}
