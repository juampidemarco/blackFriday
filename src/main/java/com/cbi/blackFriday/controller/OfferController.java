package com.cbi.blackFriday.controller;

import com.cbi.blackFriday.dao.request.OfferRequest;
import com.cbi.blackFriday.entities.Offer;
import com.cbi.blackFriday.exception.ApiException;
import com.cbi.blackFriday.exception.OfferNotFoundException;
import com.cbi.blackFriday.service.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OfferController {

    @Autowired
    private IOfferService service;

    @PostMapping("/offer")
    public ResponseEntity<Offer> offer(@RequestBody OfferRequest request) {

        Offer offer = new Offer();
        offer.setAmount(request.getAmount());
        offer.setDate(request.getDate());
        offer.setCategory(request.getCategory());
        offer.setDuration(request.getDuration());
        offer.setPrice(request.getPrice());
        offer.setImportance(request.getImportance());
        offer.setIdProd(request.getIdProd());
        offer.setIdClient(request.getIdClient());

        service.validate(offer);

        service.save(offer);

        return ResponseEntity.ok(offer);
    }

    @GetMapping("/notify/{id}")
    public ResponseEntity<List<Offer>> notify(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getOfferByClient(Math.toIntExact(id)));
    }

}
