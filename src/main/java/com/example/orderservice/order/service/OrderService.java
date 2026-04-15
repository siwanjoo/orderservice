package com.example.orderservice.order.service;

import com.example.orderservice.common.exception.OrderNotFoundException;
import com.example.orderservice.common.exception.ProductNotFoundException;
import com.example.orderservice.order.domain.Order;
import com.example.orderservice.order.domain.OrderItem;
import com.example.orderservice.order.domain.OrderStatus;
import com.example.orderservice.order.dto.CreateOrderItemRequest;
import com.example.orderservice.order.dto.CreateOrderRequest;
import com.example.orderservice.order.dto.OrderResponse;
import com.example.orderservice.order.repository.OrderRepository;
import com.example.orderservice.product.domain.Product;
import com.example.orderservice.product.repository.ProductRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        Order order = Order.builder()
                .orderStatus(OrderStatus.CREATED)
                .orderedAt(LocalDateTime.now())
                .totalPrice(BigDecimal.ZERO)
                .build();

        for (CreateOrderItemRequest itemRequest : request.items()) {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(ProductNotFoundException::new);

            product.decreaseStock(itemRequest.quantity());
            BigDecimal linePrice = product.getPrice().multiply(BigDecimal.valueOf(itemRequest.quantity()));
            totalPrice = totalPrice.add(linePrice);

            order.addOrderItem(OrderItem.of(product, itemRequest.quantity(), linePrice));
        }

        order.updateTotalPrice(totalPrice);
        Order saved = orderRepository.save(order);
        return OrderResponse.from(saved);
    }

    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findByIdWithItems(id)
                .orElseThrow(OrderNotFoundException::new);
        return OrderResponse.from(order);
    }

    @Transactional
    public OrderResponse cancelOrder(Long id) {
        Order order = orderRepository.findByIdWithItems(id)
                .orElseThrow(OrderNotFoundException::new);
        order.cancel();
        return OrderResponse.from(order);
    }
}
