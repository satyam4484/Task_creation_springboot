package com.example.demo.service;

import com.example.demo.dto.TaskCreateRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.TaskUpdateRequest;

import java.util.*;

public interface TaskService {
    TaskResponse create(TaskCreateRequest req);
    List<TaskResponse> getAll();
    TaskResponse get(Long id);
    TaskResponse update(Long id, TaskUpdateRequest req);
    void delete(Long id);
    
}
