package com.example.demo.exception;

import java.time.OffsetDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiError> handleMethodArgumentNotValid(
                        MethodArgumentNotValidException ex, HttpServletRequest req) {

                var fields = ex.getBindingResult().getFieldErrors().stream()
                                .map(f -> new ApiError.FieldError(
                                                f.getField(),
                                                f.getDefaultMessage(),
                                                f.getRejectedValue()))
                                .toList();

                return build(HttpStatus.BAD_REQUEST, "VALIDATION_FAILED",
                                "One or more fields are invalid.", req, fields);
        }

        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<ApiError> handleNotFound(NotFoundException ex, HttpServletRequest req) {
                return build(HttpStatus.NOT_FOUND, "NOT_FOUND", ex.getMessage(), req, null);
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ApiError> handleJsonParseError(
                        HttpMessageNotReadableException ex, HttpServletRequest req) {

                String message = ex.getMostSpecificCause().getMessage();
                return build(
                                HttpStatus.BAD_REQUEST,
                                "MESSAGE_NOT_READABLE",
                                message,
                                req,
                                null);
        }

        private ResponseEntity<ApiError> build(
                        HttpStatus status,
                        String code,
                        String message,
                        HttpServletRequest req,
                        List<ApiError.FieldError> fields) {
                var body = new ApiError(
                                OffsetDateTime.now(),
                                status.value(),
                                status.getReasonPhrase(),
                                code,
                                message,
                                req.getRequestURI(),
                                null, // correlationId (optional interceptor later)
                                (fields == null || fields.isEmpty()) ? null : fields);
                return ResponseEntity.status(status).contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON))
                                .body(body);
        }

}
