package com.example.eventsourcing.query.projector;

import com.example.eventsourcing.common.event.OrderCreatedEvent;
import com.example.eventsourcing.query.model.Order;
import com.example.eventsourcing.query.model.Product;
import com.example.eventsourcing.query.repository.OrderRepository;
import com.example.eventsourcing.query.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@RequiredArgsConstructor
public class OrderProjector {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    @EventHandler
    public void handle(OrderCreatedEvent event, @Timestamp Instant instant){
        Product product = productRepository.findById(event.getProductId()).orElseThrow(() -> new EntityNotFoundException());
        Order order = Order.builder()
                .id(event.getId())
                .productName(product.getName())
                .productId(product.getProductId())
                .price(product.getPrice())
                .count(event.getCount())
                .orderUser(event.getOrderUser())
                .createdAt(LocalDateTime.ofInstant(instant, ZoneOffset.UTC))
                .build();

        orderRepository.save(order);
    }

}
