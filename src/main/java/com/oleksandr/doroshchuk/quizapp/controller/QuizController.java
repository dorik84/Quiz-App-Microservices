package com.oleksandr.doroshchuk.quizapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oleksandr.doroshchuk.quizapp.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("create")
    public ResponseEntity<String> getQuiz(@RequestParam String category, @RequestParam Integer numQs, @RequestParam String title ) {
        // return new ResponseEntity<>("Hello", HttpStatus.OK);
        return quizService.createQuiz(category,numQs,title);
    }
    
}
