package com.example.app1.service;

import com.example.app1.model.ExerciseSet;
import com.example.app1.repository.ExerciseSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExerciseSetService {
    @Autowired
    private ExerciseSetRepository exerciseSetRepository;

    public void saveExerciseSet(ExerciseSet exerciseSet){this.exerciseSetRepository.save(exerciseSet);}

    public List<ExerciseSet> getExerciseSetsByWorkoutId(Long workoutId){
        return exerciseSetRepository.findByWorkoutId(workoutId);
    }

    public ExerciseSet getByExerciseId(Long exerciseId){
        return exerciseSetRepository.getByExerciseId(exerciseId);
    }

    public ExerciseSet getExerciseSetById(Long exerciseSetId){
        return exerciseSetRepository.getReferenceById(exerciseSetId);
    }

    public void deleteFromWorkout(Long exerciseSetId){
        exerciseSetRepository.deleteExerciseSet(exerciseSetId);
    }

    public List<ExerciseSet> getExerciseHistory(Long exerciseId, Long userId, LocalDate date){
        return exerciseSetRepository.getExerciseHistory(exerciseId, userId, date);
    }

}
