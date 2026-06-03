package com.sarth.placementos.repository;

import com.sarth.placementos.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Query("SELECT m FROM Module m WHERE m.track.id = :trackId")
    List<Module> findByTrackId(@Param("trackId") Long trackId);
}
