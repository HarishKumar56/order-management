package com.epam.order.repo;

import com.epam.order.entity.OrderDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDo, Long> {
}
