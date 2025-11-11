package com.example.demo.exception;

import java.time.OffsetDateTime;
import java.util.*;

public record ApiError (
    OffsetDateTime timestamp,
    int status,
    String error,
    String code,
    String message,
    String path,
    String correlationId,
    List<FieldError> filedErrors
) {
    public record FieldError(String field, String message, Object rejectValue) {}
}
