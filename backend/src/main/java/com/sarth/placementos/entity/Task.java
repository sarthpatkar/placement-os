package com.sarth.placementos.entity;


import com.sarth.placementos.enums.Status;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Task {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Long id;


private String title;


@Enumerated(EnumType.STRING)
private Status status;


private Integer plannedMinutes;


private Integer actualMinutes;


@ManyToOne
private Module module;


}