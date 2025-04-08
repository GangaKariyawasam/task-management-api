package com.coveragex.task_management.service.impl;

import com.coveragex.task_management.dto.TaskDto;
import com.coveragex.task_management.entity.Task;
import com.coveragex.task_management.entity.User;
import com.coveragex.task_management.exception.ResourceNotFoundException;
import com.coveragex.task_management.repository.TaskRepository;
import com.coveragex.task_management.repository.UserRepository;
import com.coveragex.task_management.service.TaskService;
import com.coveragex.task_management.transformer.TaskTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public TaskDto createTask(TaskDto taskDto, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Task task = TaskTransformer.toEntity(taskDto, user);
        Task savedTask = taskRepository.save(task);
        return TaskTransformer.toDto(savedTask);
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto taskDto) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (!taskOptional.isPresent()) {
            throw new ResourceNotFoundException("Task not found");
        }
        Task task = taskOptional.get();
        task.setCompleted(taskDto.getIsCompleted());
        Task updatedTask = taskRepository.save(task);
        return TaskTransformer.toDto(updatedTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (!taskOptional.isPresent()) {
            throw new ResourceNotFoundException("Task not found");
        }
        Task task = taskOptional.get();
        if (!task.isCompleted()) {
            taskRepository.delete(task);
        } else {
            throw new IllegalStateException("Task cannot be deleted because it is already completed");
        }
    }

    @Override
    public List<TaskDto> getTasksByUserEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            List<Task> tasks = taskRepository.findTop5ByUser_IdAndIsCompletedFalseOrderByCreatedAtDesc(user.getId(), Pageable.ofSize(5));

        return tasks.stream()
                .map(TaskTransformer::toDto)
                .collect(Collectors.toList());
    }
}
