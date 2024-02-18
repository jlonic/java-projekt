package com.example.app1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter @Table(name = "exercises")
public class Exercise {
    @Id @Column(name = "exercise_id") @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercises_exercise_id_seq")
    @SequenceGenerator(name = "exercises_exercise_id_seq", sequenceName = "exercises_exercise_id_seq", allocationSize = 1)
    private Long exerciseId;
    @Column(name = "name")
    private String name;
    @Column(name = "body_part")
    private String bodyPart;
}
