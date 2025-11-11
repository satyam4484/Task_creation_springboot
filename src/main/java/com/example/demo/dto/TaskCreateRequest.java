package com.example.demo.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record TaskCreateRequest (
    @NotBlank
    @Size(max = 120)
    String title,

    @NotBlank
    String description,
    
    String status,
    String priority,
    
    @FutureOrPresent(message = "dueDate must be today or in the future")
    LocalDate duDate    
) {}
