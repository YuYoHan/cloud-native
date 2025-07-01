package com.example.orderservice.domain;

public record Book(
        String isbn,
        String title,
        String author,
        Double price
) {
}
