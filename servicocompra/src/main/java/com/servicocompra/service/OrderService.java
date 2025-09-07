package com.servicocompra.service;


import com.servicocompra.dto.OrderDTO;
import com.servicocompra.model.Order;
import com.servicocompra.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.annotation.KafkaListener;
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

        Order request = new Order(order);

        Order requestSave = orderRepository.save(request);

        return new OrderDTO(requestSave);
    }
}
