package com.servicocompra.event;

import com.servicocompra.dto.ItemProductDto;
import com.servicocompra.model.ItemOrder;
import com.servicocompra.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderSaveEvent(
        Long id_buy,
        Long id_user,
        BigDecimal total_price,
        LocalDateTime date,
        List<ItemProductDto> items
) {

    public OrderSaveEvent(Order entity) {
        this(
                entity.getId_buy(),
                entity.getId_user(),
                entity.getTotal_price(),
                entity.getDate(),
                entity.getItems().stream()
                        .map(ItemProductDto::new)
                        .toList()
        );
    }

    public record ItemDto(
            String id_product,
            Integer quantity,
            BigDecimal unit_price
    ){
        public ItemDto(ItemOrder entity) {
            this (
                    entity.getId_product(),
                    entity.getQuantity(),
                    entity.getUnit_price()
            );
        }
    }
}
