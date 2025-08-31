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

    private String idProduct;
    private Integer quantity;
    private BigDecimal unitPrice;

    public ItemOrder(ItemProductDto data) {
        this.idProduct = data.idProduct();
        this.quantity = data.quantity();
        this.unitPrice = data.unitPrice();
    }
}
