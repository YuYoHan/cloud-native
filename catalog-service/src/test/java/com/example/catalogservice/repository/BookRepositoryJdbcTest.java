package com.example.catalogservice.repository;

import com.example.catalogservice.config.DataConfig;
import com.example.catalogservice.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJdbcTest
@Import(DataConfig.class)
// 테스트 컨테이너를 이용해야 하기 때문에 내장 테스트 데이터베이스 사용을 비활성해야 한다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryJdbcTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    // 데이터베이스와 상호작용하기 위한 하위 수준의 객체
    private JdbcAggregateOperations aggregateOperations;

    @Test
    void findBookByIsbnWhenExisting() {
        var bookIsbn = "1234567891";
        var book = Book.of(bookIsbn, "Title", "Author", 12.90);
        aggregateOperations.insert(book);
        Optional<Book> actualBook = bookRepository.findBuIsbn(bookIsbn);
        assertTrue(actualBook.isPresent());
        assertThat(actualBook.get().isbn()).isEqualTo(bookIsbn);
    }
}
