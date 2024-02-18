package com.example.app1.service;

import com.example.app1.model.Exercise;
import com.example.app1.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository){this.exerciseRepository=exerciseRepository;}
    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }
    public Exercise getExerciseById(Long exerciseId){
        return this.exerciseRepository.getReferenceById(exerciseId);
    }
    public List<String> selectMuscleGroups(){
        return exerciseRepository.selectMuscleGroups();
    }
}