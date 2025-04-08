package com.coveragex.task_management.controller;

import com.coveragex.task_management.dto.Create;
import com.coveragex.task_management.dto.TaskDto;
import com.coveragex.task_management.dto.Update;
import com.coveragex.task_management.service.TaskService;
import com.coveragex.task_management.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody @Validated(Create.class) TaskDto taskDto, HttpServletRequest request) {
        String token = JwtUtil.getJwtFromAuthorizationHeader(request);
        String username = jwtUtil.extractUsername(token);
        TaskDto savedTaskDto = taskService.createTask(taskDto, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTaskDto);
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long taskId, @RequestBody  @Validated(Update.class) TaskDto taskDto) {
        TaskDto updatedTaskDto = taskService.updateTask(taskId, taskDto);
        return ResponseEntity.ok(updatedTaskDto);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.ok("Task deleted successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TaskDto>> getTasksByUserEmail(HttpServletRequest request) {
        String token = JwtUtil.getJwtFromAuthorizationHeader(request);
        String username = jwtUtil.extractUsername(token);
        List<TaskDto> taskDtos = taskService.getTasksByUserEmail(username);
        return ResponseEntity.ok(taskDtos);
    }
}
