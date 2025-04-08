package com.coveragex.task_management.service;

import com.coveragex.task_management.dto.TaskDto;

import java.io.Serializable;
import java.util.List;

public interface TaskService extends Serializable {

    TaskDto createTask(TaskDto taskDto,String email);
    TaskDto updateTask(Long taskId, TaskDto taskDto);
    void deleteTask(Long taskId);
    List<TaskDto> getTasksByUserEmail(String email);
}
