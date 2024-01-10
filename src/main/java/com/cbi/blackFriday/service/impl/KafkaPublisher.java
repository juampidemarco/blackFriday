package com.cbi.blackFriday.service.impl;

import com.cbi.blackFriday.entities.Offer;
import com.cbi.blackFriday.exception.PublishException;
import com.cbi.blackFriday.service.IKafkaPublisher;
import com.cbi.blackFriday.service.IOfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaPublisher implements IKafkaPublisher {

    @Autowired
    private KafkaTemplate<String,Object> template;
    @Autowired
    private IOfferService service;
    private static final Logger log = LoggerFactory.getLogger(KafkaPublisher.class);

    public String sendMessageToTopic(Integer id){
        try{
            template.send("black-friday-topic", service.getOffersByClient(id));
        }catch (Exception e){
            log.error("error publishing message");
            return "error publishing message: "+e.getMessage();
        }
        return "Send success";
    }

    public void sendEventsToTopic(Offer offer) {
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send("black-friday-topic", offer);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.debug("Sent message=[" + offer.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    log.error("Unable to send message=[" +
                            offer.toString() + "] due to : " + ex.getMessage());
                }
            });

        } catch (Exception ex) {
            log.error("error sending messagge to topic : "+ ex.getMessage());
            throw new PublishException("error publish to topic");
        }
    }
}
