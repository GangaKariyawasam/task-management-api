package com.coveragex.task_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;

    @NotNull(groups = Create.class, message = "title is required")
    private String title;

    @NotNull(groups = Create.class, message = "description is required")
    private String description;

    @NotNull(groups = Update.class, message = "isCompleted is required")
    private Boolean isCompleted;
}
