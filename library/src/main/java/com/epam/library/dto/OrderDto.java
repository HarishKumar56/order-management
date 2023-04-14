package com.epam.library.dto;

import com.epam.library.enums.OrderStatus;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDto {

    private long orderId;
    private long amount;

    private long userId;
    private long sellerId;
    private OrderStatus orderStatus;
    private List<ProductDto> products;
}
