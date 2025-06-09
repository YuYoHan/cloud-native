package com.example.cloudnative.controller;

import com.example.cloudnative.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    void whenPostRequestThenBookCreated() {
        var expectedBook = new Book("12345678901234567890123", "Book Title", "Author",9.90);

        webClient.post()
                .uri("/books")
                .bodyValue(expectedBook)
                .exchange()
                .expectStatus().isCreated()     // HTTP 응답이 201 생성 상태를 갖는지 확인
                .expectBody(Book.class).value(actualBook -> {
                    Assertions.assertThat(actualBook).isNotNull();  // HTTP 응답의 본문이 null이 아닌지 확인
                    Assertions.assertThat(actualBook.isbn())
                            .isEqualTo(expectedBook.isbn());
                });
    }
}