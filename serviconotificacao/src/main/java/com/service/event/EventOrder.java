package com.service.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record EventOrder(
        Long id_buy,
        Long id_user,
        BigDecimal total_price,
        LocalDateTime date,
        List<ItemDto> items) {

    public record ItemDto(
            String id_product,
            Integer quantity,
            BigDecimal unit_price
    ) {
    }
}
