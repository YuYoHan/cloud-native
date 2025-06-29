package com.example.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Book (
        @Id
        Long id,
        @NotBlank(message = "The book ISBN must be defined")
        @Pattern(
                regexp = "^([0-9]{10}[0-9]{13})$",
                message = "The ISBN format must be valid"
        )
        String isbn,
        @NotBlank(message = "The book title must be defined")
        String title,
        @NotBlank(message = "The book author must be defined")
        String author,
        @NotNull(message = "The book price must be defined")
        @Positive(message = "The book price must be greater than zero") // 0보다 큰 값을 가져와야 한다.
        Double price,
        String publisher,
        @CreatedDate
        Instant createdDate,
        @LastModifiedDate
        Instant lastModifiedDate,
        // 낙관적인 잠금을 위해 사용되는 엔티티 버전 번호
        @Version
        int version
){
        public static Book of(String isbn, String title, String author, Double price, String publisher) {
                // ID가 null이고 버전이 0이면 새로운 엔티티로 인식
                return new Book(
                        null, title, isbn, author,  price, publisher, null, null, 0
                );
        }
}
