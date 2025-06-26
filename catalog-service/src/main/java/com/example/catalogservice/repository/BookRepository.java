package com.example.catalogservice.repository;

import com.example.catalogservice.domain.Book;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findBuIsbn(String isbn);
    boolean existsByIsbn(String isbn);

    // 데이터베이스 상태를 수정할 연산임을 나타낸다.
    @Modifying
    @Query("delete from Book where isbn = :isbn")
    void deleteByIsbn(String isbn);
}
