package com.example.app1.repository;

import com.example.app1.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Query("SELECT DISTINCT  e.bodyPart FROM Exercise e ORDER BY e.bodyPart")
    List<String> selectMuscleGroups();

}
