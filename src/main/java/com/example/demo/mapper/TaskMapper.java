package com.example.demo.mapper;

import org.mapstruct.*;
import com.example.demo.entity.*;

import com.example.demo.dto.TaskCreateRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.TaskUpdateRequest;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Task toEntity(TaskCreateRequest req);

    TaskResponse toResponse(Task entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatEntityFromDto(TaskUpdateRequest req, @MappingTarget Task entity);

}
