package com.servicocompra.model;


import com.servicocompra.dto.ItemProductDto;
import com.servicocompra.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
@Table(name = "request")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_buy;
    private Long id_user;
    private BigDecimal total_price;

    @CreationTimestamp
    private LocalDateTime date;

    @ElementCollection
    @CollectionTable(name = "request_items", joinColumns = @JoinColumn(name = "request_id"))
    private List<ItemOrder> items;

    public Order(OrderDTO dto) {
        this.id_user = dto.id_user();
        this.total_price = dto.total_price();
        if (dto.items() != null) {
            this.items = dto.items().stream()
                    .map(ItemOrder::new)
                    .toList();
        }
    }
}
