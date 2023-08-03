package com.example.eventsourcing.command.dto;

import lombok.Data;

@Data
public class ProductAddStockDto {

    private String productId;
    private Long stock;

}
