package com.servicocompra.service;


import com.servicocompra.dto.OrderDTO;
import com.servicocompra.model.Order;
import com.servicocompra.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderDTO serviceSubmit(OrderDTO order) {
        Order request = new Order(order);

        Order requestSave = orderRepository.save(request);

        return new OrderDTO(requestSave);
    }
}
