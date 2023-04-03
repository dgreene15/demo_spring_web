package com.example.demo.webclient.exceptions;

import com.example.demo.webclient.domain.ArticlePost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ArticleGlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ArticleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFound(ArticleNotFoundException ex) {
        ErrorResponse error = ErrorResponse.builder().status(HttpStatus.NOT_FOUND.value()).message("Article Not Found").build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
