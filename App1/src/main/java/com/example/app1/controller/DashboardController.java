package com.example.app1.controller;

import com.example.app1.model.Exercise;
import com.example.app1.model.ExerciseSet;
import com.example.app1.model.Workout;
import com.example.app1.service.ExerciseSetService;
import com.example.app1.service.WorkoutService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class DashboardController {
    private final WorkoutService workoutService;
    private final ExerciseSetService exerciseSetService;

    @Autowired
    public DashboardController(WorkoutService workoutService,
                               ExerciseSetService exerciseSetService){
        this.workoutService=workoutService;
        this.exerciseSetService=exerciseSetService;}

    @GetMapping("/dashboard")
    public String dashboard(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user") != null) {
            Long userId = (Long) httpSession.getAttribute("userId");
            Workout workoutToday = workoutService.findWorkoutByDateAndUser(LocalDate.now(), userId);
            if (workoutToday!=null){
                List<ExerciseSet> exerciseSets = exerciseSetService.getExerciseSetsByWorkoutId(workoutToday.getWorkoutId());
                List <Exercise> exercises = new ArrayList<>();
                for (ExerciseSet es : exerciseSets) {
                    exercises.add(es.getExercise());
                }
                exercises.sort(Comparator.comparing(Exercise::getExerciseId)); //sorted to always display the same order
                exerciseSets.sort(Comparator.comparing(es -> es.getExercise().getExerciseId()));

                httpSession.setAttribute("workout", workoutToday);
                model.addAttribute("exercises", exercises);
                model.addAttribute("exerciseSets", exerciseSets);
            }
            model.addAttribute("workoutToday", workoutToday);
            model.addAttribute("today", LocalDate.now());

            return "dashboard";
        } else return "redirect:/login";
    }
}