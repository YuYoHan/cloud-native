package com.example.cloudnative.domain;

public record Book (
        String isbn,
        String title,
        String author,
        Double price
){}
