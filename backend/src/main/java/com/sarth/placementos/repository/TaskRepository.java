package com.sarth.placementos.repository;

import com.sarth.placementos.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.module.id = :moduleId")
    List<Task> findByModuleId(@Param("moduleId") Long moduleId);

    @Query("""
            SELECT t FROM Task t
            WHERE t.module.track.user.id = :userId
            AND t.status <> 'COMPLETED'
            """)
    List<Task> findActiveTasks(@Param("userId") Long userId);

    @Query("""
            SELECT COUNT(t) FROM Task t
            WHERE t.module.track.user.id = :userId
            """)
    long countUserTasks(@Param("userId") Long userId);

    @Query("""
            SELECT COUNT(t) FROM Task t
            WHERE t.module.track.user.id = :userId
            AND t.status = 'COMPLETED'
            """)
    long countCompletedTasks(@Param("userId") Long userId);
}
