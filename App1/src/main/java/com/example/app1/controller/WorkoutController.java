package com.example.app1.controller;

import com.example.app1.model.Exercise;
import com.example.app1.model.ExerciseSet;
import com.example.app1.model.User;
import com.example.app1.model.Workout;
import com.example.app1.repository.ExerciseRepository;
import com.example.app1.repository.UserRepository;
import com.example.app1.repository.WorkoutRepository;
import com.example.app1.service.ExerciseService;
import com.example.app1.service.ExerciseSetService;
import com.example.app1.service.WorkoutService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller @RequestMapping("/workouts")
public class WorkoutController {
    private final ExerciseService exerciseService;
    private final ExerciseSetService exerciseSetService;
    private final WorkoutService workoutService;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutController(ExerciseService exerciseService,
                             ExerciseSetService exerciseSetService,
                             WorkoutService workoutService,
                             UserRepository userRepository,
                             ExerciseRepository exerciseRepository,
                             WorkoutRepository workoutRepository){
        this.exerciseService=exerciseService;
        this.exerciseSetService=exerciseSetService;
        this.workoutService=workoutService;
        this.userRepository=userRepository;
        this.exerciseRepository=exerciseRepository;
        this.workoutRepository=workoutRepository;
    }

    @GetMapping("/create")
    public String createWorkoutForm(HttpSession httpSession){
        if (httpSession.getAttribute("user") != null) {
            return "createWorkout";
        } else return "redirect:/login";
    }

    @PostMapping("/editWorkout")
    public String createWorkout(@ModelAttribute("workout") Workout workout,
                                HttpSession httpSession, Model model,
                                @RequestParam("workoutDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate workoutDate) {
        if (httpSession.getAttribute("user") == null)
            return "redirect:/login";

        Long id = (Long) httpSession.getAttribute("userId");
        User user = userRepository.getReferenceById(id);

        Workout existingWorkout = workoutService.findWorkoutByDateAndUser(workoutDate, id);
        if (existingWorkout==null) {
            workout.setUser(user);
            workout.setDate(workoutDate);
            httpSession.setAttribute("workout", workout);
            workoutService.createWorkout(workout);

            return "createWorkout";
        }
        else return getExistingWorkout(httpSession, model, existingWorkout);
    }

    @GetMapping("/selectMuscleGroup")
    public String selectMuscleGroup(Model model, HttpSession httpSession){
        if (httpSession.getAttribute("user") == null)
            return "redirect:/login";

        List<String> muscleGroups = exerciseRepository.selectMuscleGroups();
        model.addAttribute("muscleGroups", muscleGroups);

        return "/selectMuscleGroup";
    }


    @PostMapping("/addExercise")
    public String addExercise(@RequestParam("bodyPart") String bodyPart, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null)
            return "redirect:/login";

        List<Exercise> exercises = exerciseService.getAllExercises();

        model.addAttribute("exercises", exercises);
        model.addAttribute("bodyPart", bodyPart);
        return "addExercise";
    }


    @PostMapping("/exerciseSet")
    public String addExerciseSet(@RequestParam("exerciseId") Long exerciseId, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null)
            return "redirect:/login";

        Exercise exercise = exerciseRepository.getReferenceById(exerciseId);
        ExerciseSet exerciseSet = new ExerciseSet();
        exerciseSet.setExercise(exercise);
        exerciseSet.setWorkout((Workout) httpSession.getAttribute("workout"));

        httpSession.setAttribute("exerciseId", exercise.getExerciseId());
        model.addAttribute("exercise", exercise);
        model.addAttribute("exerciseSet", exerciseSet);
        return "exerciseSet";
    }

    @PostMapping("/saveExerciseSet")
    public String saveExerciseSet(@ModelAttribute("exerciseSet") ExerciseSet exerciseSet, HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user") == null)
            return "redirect:/login";

        Workout w = (Workout) httpSession.getAttribute("workout");
        Workout workout = workoutRepository.getReferenceById(w.getWorkoutId());

        Long exerciseId = (Long) httpSession.getAttribute("exerciseId");
        Exercise exercise = exerciseRepository.getReferenceById(exerciseId);

        exerciseSet.setWorkout(workout);
        exerciseSet.setExercise(exercise);
        this.exerciseSetService.saveExerciseSet(exerciseSet);


        return getExistingWorkout(httpSession, model, workout);
    }

    @GetMapping("/editExercise")
    public String editExerciseForm(@RequestParam("exerciseId") Long exerciseId, Model model, HttpSession httpSession){
        if (httpSession.getAttribute("user") == null)
            return "redirect:/login";

        ExerciseSet exerciseSet = exerciseSetService.getByExerciseId(exerciseId);
        httpSession.setAttribute("exerciseSetId", exerciseSet.getSetId());
        model.addAttribute("exercise", exerciseSet.getExercise());
        model.addAttribute("exerciseSet", exerciseSet);
        return "editExercise";
    }

    @PostMapping("/editExercise")
    public String editExercise(@RequestParam Double weight,
                               @RequestParam Integer repetitions,
                               HttpSession httpSession, Model model){
        if (httpSession.getAttribute("user") == null)
            return "redirect:/login";

        ExerciseSet exerciseSet = exerciseSetService.getExerciseSetById((Long) httpSession.getAttribute("exerciseSetId"));
        exerciseSet.setWeight(weight);
        exerciseSet.setRepetitions(repetitions);
        exerciseSetService.saveExerciseSet(exerciseSet);


        Workout w = (Workout) httpSession.getAttribute("workout");
        Workout existingWorkout = workoutRepository.getReferenceById(w.getWorkoutId());

        return getExistingWorkout(httpSession, model, existingWorkout);
    }

    public String getExistingWorkout(HttpSession httpSession, Model model, Workout existingWorkout) {
        if (httpSession.getAttribute("user") == null)
            return "redirect:/login";

        List<ExerciseSet> exerciseSets = exerciseSetService.getExerciseSetsByWorkoutId(existingWorkout.getWorkoutId());
        List <Exercise> exercises = new ArrayList<>();
        for (ExerciseSet es : exerciseSets) {
            exercises.add(es.getExercise());
        }
        exercises.sort(Comparator.comparing(Exercise::getExerciseId)); //sorted to always display the same order
        exerciseSets.sort(Comparator.comparing(es -> es.getExercise().getExerciseId()));

        httpSession.setAttribute("workout", existingWorkout);
        model.addAttribute("existingWorkout", existingWorkout);
        model.addAttribute("exercises", exercises);
        model.addAttribute("exerciseSets", exerciseSets);

        return "existingWorkout";
    }

    @PostMapping("/removeExercise")
    public String removeExerciseFromWorkout(@RequestParam("exerciseId") Long exerciseId, HttpSession httpSession, Model model){
        if (httpSession.getAttribute("user") == null)
            return "redirect:/login";

        ExerciseSet exerciseSet = exerciseSetService.getByExerciseId(exerciseId);
        exerciseSetService.deleteFromWorkout(exerciseSet.getSetId());

        Workout w = (Workout) httpSession.getAttribute("workout");
        Workout existingWorkout = workoutRepository.getReferenceById(w.getWorkoutId());

        return getExistingWorkout(httpSession, model, existingWorkout);
    }

    @PostMapping("/removeAllExercises")
    public String removeAllExercisesFromWorkout(@RequestParam("workoutId") Long workoutId, HttpSession httpSession, Model model){
        if (httpSession.getAttribute("user") == null)
            return "redirect:/login";

        workoutRepository.deleteAllExercisesFromWorkout(workoutId);
        Workout existingWorkout = workoutRepository.getReferenceById(workoutId);

        return getExistingWorkout(httpSession, model, existingWorkout);
    }

    @PostMapping("/deleteWorkout")
    public String deleteWorkout(@RequestParam("workoutId") Long workoutId, HttpSession httpSession){
        if (httpSession.getAttribute("user") == null)
            return "redirect:/login";

        workoutRepository.deleteAllExercisesFromWorkout(workoutId);
        workoutRepository.deleteWorkout(workoutId);

        return "dashboard";
    }

    @PostMapping("/addNotes")
    public String addNotes(@RequestParam("workoutId") Long workoutId, @RequestParam("notes") String notes, HttpSession httpSession, Model model){
        if (httpSession.getAttribute("user") == null)
            return "redirect:/login";

        Workout workout = workoutRepository.getReferenceById(workoutId);
        workout.setNotes(notes);
        workoutRepository.save(workout);

        return getExistingWorkout(httpSession, model, workout);
    }

    @PostMapping("/deleteNotes")
    public String deleteNotes(@RequestParam("workoutId") Long workoutId, HttpSession httpSession, Model model){
        if (httpSession.getAttribute("user") == null)
            return "redirect:/login";

        Workout workout = workoutRepository.getReferenceById(workoutId);
        workout.setNotes(null);
        workoutRepository.save(workout);

        return getExistingWorkout(httpSession, model, workout);
    }


}
/*TODO
FILTER EXERCISES BY BODYPART MOZDA ? (MOZDA USELESS)
COMPARE WITH OLD DATA TO SEE PROGRESS (LISTA SVIH (starih) VJEZBI SORTIRANA DATUMIMA),
    KLIK NA DATUM -> OTVORI STARI WORKOUT I USPOREDI (s cim/kako???)

CARDIO TABLE MOZDA?? treba novi table u db

COPY PREVIOUS WORKOUT (MOZDA?)
TRACK PERSONAL RECORD MOZDA? (<mozda prekomplicirano s mojon db>
    <treba useru dodati exerciseset listu koja bi pamtila>


CSS SREDITI (mozda tailwindcss?)

???

 */