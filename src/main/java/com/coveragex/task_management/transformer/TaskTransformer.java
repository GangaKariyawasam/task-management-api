package com.coveragex.task_management.transformer;

import com.coveragex.task_management.dto.TaskDto;
import com.coveragex.task_management.entity.Task;
import com.coveragex.task_management.entity.User;

public class TaskTransformer {

    public static TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setIsCompleted(task.isCompleted());
        return dto;
    }

    public static Task toEntity(TaskDto dto, User user) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setUser(user);
        return task;
    }
}
