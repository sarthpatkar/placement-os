package com.sarth.placementos.repository;

import com.sarth.placementos.entity.DailyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyLogRepository extends JpaRepository<DailyLog, Long> {

    @Query("SELECT d FROM DailyLog d WHERE d.user.id = :userId AND d.date = :date")
    Optional<DailyLog> findByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);
}
