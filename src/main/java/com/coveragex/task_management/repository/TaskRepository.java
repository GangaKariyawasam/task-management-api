package com.coveragex.task_management.repository;

import com.coveragex.task_management.entity.Task;
import com.coveragex.task_management.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    List<Task> findTop5ByUser_IdAndIsCompletedFalseOrderByCreatedAtDesc(@Param("userId") Long userId, Pageable pageable);
}
