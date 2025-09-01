package com.servicocompra.model;


import com.servicocompra.dto.ItemProductDto;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
public class ItemOrder {

    private String id_product;
    private Integer quantity;
    private BigDecimal unit_price;

    public ItemOrder(ItemProductDto data) {
        this.id_product = data.id_product();
        this.quantity = data.quantity();
        this.unit_price = data.unit_price();
    }
}
