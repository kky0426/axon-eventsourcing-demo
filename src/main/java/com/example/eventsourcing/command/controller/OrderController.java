package com.example.eventsourcing.command.controller;

import com.example.eventsourcing.command.command.OrderCreateCommand;
import com.example.eventsourcing.command.dto.OrderCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final CommandGateway commandGateway;

    @PostMapping
    public CompletableFuture<String> createOrder(@RequestBody OrderCreateDto dto){
        String id = UUID.randomUUID().toString();
        OrderCreateCommand command = OrderCreateCommand.builder()
                .id(id)
                .productId(dto.getProductId())
                .count(dto.getCount())
                .orderUser(dto.getOrderUser())
                .build();
        return commandGateway.send(command);
    }

}
