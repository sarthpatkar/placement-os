package com.sarth.placementos.entity;

import com.sarth.placementos.enums.GoalType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "goals")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Column(nullable = false)
    private String title;


    private String description;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GoalType type;


    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;


    private LocalDate targetDate;


    private LocalDateTime createdAt;


    @PrePersist
    void create() {
        createdAt = LocalDateTime.now();
    }
}