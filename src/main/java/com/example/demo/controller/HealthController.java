package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.HealthResponse;
import java.time.OffsetDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<HealthResponse> health() {
        var body = new HealthResponse(
            "UP",
            "taskflow-service",
            "v1",
            OffsetDateTime.now()
        );
        // return ResponseEntity.badRequest().body(body);
        return ResponseEntity.ok(body);
    }
    
    
}
