package com.example.demo.entity;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;
    
    private LocalDate dueDate;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    public enum Status {
        OPEN, IN_PROGRESS, DONE
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    @PrePersist
    void onCreate() {
        if (status == null)
            status = Status.OPEN;
        if (priority == null)
            priority = Priority.MEDIUM;
        createdAt = OffsetDateTime.now();
    }
}

// id, title, description, status, priority, dueDate, createdAt