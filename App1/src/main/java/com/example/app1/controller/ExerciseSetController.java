package com.example.app1.controller;

import com.example.app1.model.ExerciseSet;
import com.example.app1.service.ExerciseSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/exerciseSets")
public class ExerciseSetController {
    private final ExerciseSetService exerciseSetService;

    @Autowired
    public ExerciseSetController(ExerciseSetService exerciseSetService){this.exerciseSetService=exerciseSetService;}

}
