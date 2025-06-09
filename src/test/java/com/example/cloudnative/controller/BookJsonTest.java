package com.example.cloudnative.controller;

import com.example.cloudnative.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.springframework.boot.test.json.JsonContentAssert.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JsonTest
public class BookJsonTest {
    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = new Book("1234567890123", "title", "Author", 9.90);
        var jsonContent = json.write(book);


        String jsonStr = jsonContent.getJson();

        assertThat(jsonStr).contains("\"isbn\":\"1234567890123\"");
        assertThat(jsonStr).contains("\"title\":\"title\"");
        assertThat(jsonStr).contains("\"author\":\"Author\"");
        assertThat(jsonStr).contains("\"price\":9.90");
    }

    @Test
    void testDeserialize() throws Exception {
        String content = """
        {
          "isbn": "1234567890123",
          "title": "title",
          "author": "Author",
          "price": 9.90
        }
        """;

        var book = json.parse(content).getObject();
        assertThat(book.isbn()).isEqualTo("1234567890123");
        assertThat(book.title()).isEqualTo("title");
        assertThat(book.author()).isEqualTo("Author");
        assertThat(book.price()).isEqualTo(9.90);
    }
}
