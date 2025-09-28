package com.service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @KafkaListener(topics = "notificacap-topico", groupId = "notificacao-group" )
    public void listen(String message) {
        System.out.println("Received Message in group 'notificacao-group': " + message);
    }
}
