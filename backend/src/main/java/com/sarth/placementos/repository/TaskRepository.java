package com.sarth.placementos.repository;


import com.sarth.placementos.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task,Long>{

}