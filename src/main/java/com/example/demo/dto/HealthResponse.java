package com.example.demo.dto;

import java.time.OffsetDateTime;

public record HealthResponse(
        String status,
        String service,
        String version,
        OffsetDateTime timestamp
){}
