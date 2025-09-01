package com.servicocompra.controller;


import com.servicocompra.dto.OrderDTO;
import com.servicocompra.service.OrderService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/request")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> submit(@RequestBody @Valid OrderDTO dto) {
        OrderDTO newDto = orderService.serviceSubmit(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDto);
    }
}
