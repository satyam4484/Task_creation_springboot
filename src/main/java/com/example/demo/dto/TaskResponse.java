package com.example.demo.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record TaskResponse(
    Long id,
    String title,
    String description,
    String status,
    String priority,
    LocalDate duDate,
    OffsetDateTime createdAt
) {}


