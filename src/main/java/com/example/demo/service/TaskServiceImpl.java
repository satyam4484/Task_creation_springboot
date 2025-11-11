package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.exception.*;

import com.example.demo.dto.TaskCreateRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.TaskUpdateRequest;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.repository.TaskRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.*;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repo;
    private final TaskMapper mapper;


    @Override
    @Transactional
    public TaskResponse create(TaskCreateRequest req) {
        Task entity = mapper.toEntity(req);
        Task saved = repo.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public List<TaskResponse>getAll() {
        List<TaskResponse> tasks = repo.findAll().stream().map(mapper::toResponse).toList();
        return tasks;
    }

    @Override
    @Transactional
    public TaskResponse get(Long id) {
        Task task = repo.findById(id).orElseThrow(() -> new NotFoundException("Task %d not found".formatted(id)));
        return mapper.toResponse(task);
    }

    @Override
    @Transactional
    public TaskResponse update(Long id,TaskUpdateRequest req) {
        Task entityToBeUpdated = repo.findById(id).orElseThrow(() -> new NotFoundException("Task %d not found".formatted(id)));

        mapper.updatEntityFromDto(req, entityToBeUpdated);

        Task saved = repo.save(entityToBeUpdated);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(!repo.existsById(id)) {
            throw new NotFoundException("Task %d not found".formatted(id));
        }
        repo.deleteById(id);
    }
    
}
