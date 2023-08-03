package com.example.eventsourcing.query.repository;

import com.example.eventsourcing.query.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
