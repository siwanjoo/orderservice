package com.example.orderservice.order.repository;

import com.example.orderservice.order.domain.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
            select distinct o
            from Order o
            left join fetch o.orderItems oi
            left join fetch oi.product
            where o.id = :id
            """)
    Optional<Order> findByIdWithItems(@Param("id") Long id);
}
