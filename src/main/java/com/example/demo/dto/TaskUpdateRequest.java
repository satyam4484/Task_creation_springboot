package com.example.demo.dto;

import com.example.demo.entity.Task.Priority;
import com.example.demo.entity.Task.Status;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskUpdateRequest (
    @Size(max = 120)
    String title,

    @Size(max = 1000)
    String description,

    Status status,
    Priority priority,

    @FutureOrPresent(message = "dueDate must be today or in the future")
    LocalDate dueDate
) {}
