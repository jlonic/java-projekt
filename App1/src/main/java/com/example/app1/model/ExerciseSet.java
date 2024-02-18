package com.example.app1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity @Getter @Setter @Table(name = "exercise_sets")
public class ExerciseSet {
    @Id @Column(name = "set_id") @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_sets_set_id_seq")
    @SequenceGenerator(name = "exercise_sets_set_id_seq", sequenceName = "exercise_sets_set_id_seq", allocationSize = 1)
    private Long setId;
    @ManyToOne @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;
    @ManyToOne @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;
    @Column(name = "repetitions")
    private int repetitions;
    @Column(name = "weight")
    private double weight;

    public LocalDate getWorkoutDate() {
        return this.workout.getDate();
    }

}
