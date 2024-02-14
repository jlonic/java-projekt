package com.example.app1.service;

import com.example.app1.model.Workout;
import com.example.app1.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository){this.workoutRepository=workoutRepository;}
    public void createWorkout(Workout workout){workoutRepository.save(workout);}

    public Workout findWorkoutByDateAndUser(LocalDate date, Long userId){
        return workoutRepository.findWorkoutByDateAndUser(date, userId);
    }
    public void deleteAllExercisesFromWorkout(Long workoutId){
        workoutRepository.deleteAllExercisesFromWorkout(workoutId);
    }
    public void deleteWorkout(Long workoutId){
        workoutRepository.deleteWorkout(workoutId);
    }
    public Workout getById(Long workoutId){
        return workoutRepository.getReferenceById(workoutId);
    }
    public void save(Workout workout){
        workoutRepository.save(workout);
    }

    public List<Workout> findWorkoutsBeforeToday(LocalDate date, Long userId){
        return workoutRepository.findWorkoutsBeforeToday(date, userId);
    }

}
