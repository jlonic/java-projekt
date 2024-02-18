package com.example.app1.repository;

import com.example.app1.model.Workout;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    @Query("SELECT w FROM Workout w JOIN w.user u WHERE w.date = :date AND u.userId = :userId")
    Workout findWorkoutByDateAndUser(LocalDate date, Long userId);
    @Modifying @Transactional @Query("DELETE FROM ExerciseSet e WHERE e.workout.workoutId = :workoutId")
    void deleteAllExercisesFromWorkout(Long workoutId);
    @Modifying @Transactional @Query("DELETE FROM Workout w WHERE w.workoutId = :workoutId")
    void deleteWorkout(Long workoutId);
    @Query("SELECT w FROM Workout w JOIN w.user u WHERE w.date < :date AND u.userId = :userId ")
    List<Workout> findWorkoutsBeforeToday(LocalDate date, Long userId);
    @Query("SELECT w FROM Workout w JOIN w.user u WHERE w.date > :date AND u.userId = :userId ")
    List<Workout> findWorkoutsAfterToday(LocalDate date, Long userId);
}