package com.example.app1.service;

import com.example.app1.model.Workout;
import com.example.app1.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    public void createWorkout(Workout workout){workoutRepository.save(workout);}

    public Workout findWorkoutByDateAndUser(LocalDate date, Long userId){
        return workoutRepository.findWorkoutByDateAndUser(date, userId);
    }


}
