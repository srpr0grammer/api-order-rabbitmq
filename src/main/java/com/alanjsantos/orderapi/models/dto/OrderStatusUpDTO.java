package com.alanjsantos.orderapi.models.dto;

import com.alanjsantos.orderapi.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpDTO {

    @NotNull(message = "Order ID cannot be null")
    private Long id;
    @NotBlank(message = "Status cannot be blank")
    private OrderStatus status;
}
