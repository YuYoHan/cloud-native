package com.example.catalogservice.controller;

import com.example.catalogservice.config.PolarProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final PolarProperties polarProperties;

    @GetMapping("/")
    public String home() {
        return polarProperties.getGreeting();
    }
}
