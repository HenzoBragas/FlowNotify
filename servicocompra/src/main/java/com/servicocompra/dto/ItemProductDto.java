package com.servicocompra.dto;

import com.servicocompra.model.ItemOrder;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemProductDto(
        @NotBlank String id_product,
        @NotNull Integer quantity,
        @NotNull @Digits(integer = 10, fraction = 2) BigDecimal unit_price) {
    public ItemProductDto(ItemOrder entity) {
        this(
                entity.getId_product(),
                entity.getQuantity(),
                entity.getUnit_price());
    }

}
