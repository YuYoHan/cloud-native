package com.example.cloudnative;

import com.example.cloudnative.domain.Book;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class BookValidationTest {
    private static Validator validator;

    // 클래스 내의 테스트를 실행하기 전에 가장 먼저 실행할 코드 블록임을 나타낸다.
    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        var book = new Book("a234567890", "title", "author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();       // 유효성 검사에서 오류가 없음을 확인한다.
    }

    @Test
    void whenAllFieldsCorrectThenValidationFails() {
        var book = new Book("a234567890", "title", "author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid");
    }
}
