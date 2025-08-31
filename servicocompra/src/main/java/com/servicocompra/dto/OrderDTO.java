package com.servicocompra.dto;

import com.servicocompra.model.ItemOrder;
import com.servicocompra.model.Order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.util.List;

public record OrderDTO(
        @NotBlank String id_buy,
        @NotNull Long id_user,
        @NotNull @Digits(integer = 10, fraction = 2) BigDecimal totalprice,
        @NotNull @Valid List<ItemProductDto> items
) {
    public OrderDTO(Order entity) {
        this( // "this" chama o construtor principal do record
                entity.getId_buy(),
                entity.getId_user(),
                entity.getTotal_price(),
                entity.getItems().stream()
                        .map(ItemProductDto::new)
                        .toList()
        );
    }
}
