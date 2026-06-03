package com.sarth.placementos.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Module {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Long id;


private String title;


private Integer progress=0;


@ManyToOne
private Track track;


}