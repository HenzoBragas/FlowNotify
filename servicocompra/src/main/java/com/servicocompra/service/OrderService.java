package com.servicocompra.service;


import com.servicocompra.dto.OrderDTO;
import com.servicocompra.event.OrderSaveEvent;
import com.servicocompra.model.Order;
import com.servicocompra.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    private final StreamBridge streamBridge;

    public OrderService(OrderRepository orderRepository, StreamBridge streamBridge) {
        this.orderRepository = orderRepository;
        this.streamBridge = streamBridge;
    }

    @Transactional
    public OrderDTO serviceSubmit(OrderDTO order) {

        log.info("Recebendo requisição");

        Order request = new Order(order);

        Order requestSave = orderRepository.save(request);

        OrderSaveEvent event = new OrderSaveEvent(requestSave);

        Message<OrderSaveEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.KEY, String.valueOf(event.id_buy()))
                .build();

        log.info("Enviando evento para Kafka com a chave: {}", event.id_buy());
        streamBridge.send("enviarPedido-out-0", message);
        log.info("Evento enviado com sucesso");

        return new OrderDTO(requestSave);
    }
}
