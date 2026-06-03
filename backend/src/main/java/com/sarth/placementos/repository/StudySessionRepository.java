package com.sarth.placementos.repository;

import com.sarth.placementos.entity.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {

    @Query("SELECT s FROM StudySession s WHERE s.user.id = :userId ORDER BY s.startTime DESC")
    List<StudySession> findByUserId(@Param("userId") Long userId);

    @Query("""
            SELECT s FROM StudySession s
            WHERE s.user.id = :userId
              AND s.startTime >= :dayStart
              AND s.startTime < :dayEnd
            ORDER BY s.startTime DESC
            """)
    List<StudySession> findByUserIdAndDate(
            @Param("userId") Long userId,
            @Param("dayStart") LocalDateTime dayStart,
            @Param("dayEnd") LocalDateTime dayEnd);
}
