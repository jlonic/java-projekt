package com.example.app1.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity @Table(name = "workouts") @Getter @Setter
public class Workout {
    @Id @Column(name = "workout_id") @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workouts_seq")
    @SequenceGenerator(name = "workouts_seq", sequenceName = "workouts_seq", allocationSize = 1)
    private Long workoutId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Column(name = "notes")
    private String notes;
}
