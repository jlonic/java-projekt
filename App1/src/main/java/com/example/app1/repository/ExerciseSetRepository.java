package com.example.app1.repository;

import com.example.app1.model.ExerciseSet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {
    @Query("SELECT e FROM ExerciseSet e WHERE e.workout.workoutId = :workoutId")
    List<ExerciseSet> findByWorkoutId(Long workoutId);

    @Query("SELECT e FROM ExerciseSet e WHERE e.exercise.exerciseId = :exerciseId")
    ExerciseSet getByExerciseId(Long exerciseId);

    @Modifying @Transactional @Query("DELETE FROM ExerciseSet e WHERE e.setId = :exerciseSetId")
    void deleteExerciseSet(Long exerciseSetId);


}
