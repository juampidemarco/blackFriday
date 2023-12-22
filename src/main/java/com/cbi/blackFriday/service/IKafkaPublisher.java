package com.cbi.blackFriday.service;

import com.cbi.blackFriday.entities.Offer;

public interface IKafkaPublisher {

    String sendMessageToTopic(Integer id);
    void sendEventsToTopic(Offer offer);
}
