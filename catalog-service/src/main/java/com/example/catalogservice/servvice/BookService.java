package com.example.catalogservice.servvice;

import com.example.catalogservice.domain.Book;
import com.example.catalogservice.exception.BookAlreadyExistsException;
import com.example.catalogservice.exception.BookNotFoundException;
import com.example.catalogservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findBuIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if(bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book updateBook(String isbn, Book book) {
        return bookRepository.findBuIsbn(isbn)
                .map(existingBook -> {
                    var bookToUpdate = new Book(
                            existingBook.id(),
                            existingBook.isbn(),
                            book.title(),
                            book.author(),
                            book.price(),
                            existingBook.publisher(),
                            existingBook.createdDate(),
                            existingBook.lastModifiedDate(),
                            existingBook.version()
                    );
                    return bookRepository.save(bookToUpdate);
                }).orElseGet(() -> addBookToCatalog(book)); // 카탈로그에 존재하지 않는 책을 수정하려고 하면 새로운 책을 만든다.
    }
}
