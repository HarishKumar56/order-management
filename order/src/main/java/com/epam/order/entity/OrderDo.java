package com.epam.order.entity;

import com.epam.library.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class OrderDo {

    @Id
    @GeneratedValue
    private long orderId;

    private long userId;

    private long amount;


    @Enumerated
    private OrderStatus orderStatus;

}
