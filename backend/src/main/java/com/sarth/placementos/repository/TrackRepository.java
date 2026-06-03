package com.sarth.placementos.repository;

import com.sarth.placementos.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Long> {

    @Query("SELECT t FROM Track t WHERE t.user.id = :userId")
    List<Track> findByUserId(@Param("userId") Long userId);


    @Query("""
            SELECT t FROM Track t
            WHERE t.user.id = :userId
            AND t.archived = false
            """)
    List<Track> findByUserIdAndArchivedFalse(
            @Param("userId") Long userId
    );
}
