package com.alanjsantos.orderapi.models.entity;

import com.alanjsantos.orderapi.models.enums.OrderStatus;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity (name = "tb_order")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnore
    private List<Product> products = new ArrayList<>();
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateOrder;
    private OrderStatus statusOrder;
}
