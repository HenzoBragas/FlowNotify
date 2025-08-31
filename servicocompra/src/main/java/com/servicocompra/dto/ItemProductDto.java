package com.servicocompra.dto;

import com.servicocompra.model.ItemOrder;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemProductDto(
        @NotBlank String idProduct,
        @NotNull Integer quantity,
        @NotNull @Digits(integer = 10, fraction = 2) BigDecimal unitPrice) {
    public ItemProductDto(ItemOrder entity) {
        this(
                entity.getIdProduct(),
                entity.getQuantity(),
                entity.getUnitPrice());
    }

}
