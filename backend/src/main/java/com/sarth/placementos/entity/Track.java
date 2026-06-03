package com.sarth.placementos.entity;


import com.sarth.placementos.enums.TrackType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Track {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    @Enumerated(EnumType.STRING)
    private TrackType type;


    @ManyToOne
    private User user;

}