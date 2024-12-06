package com.oleksandr.doroshchuk.quizapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.oleksandr.doroshchuk.quizapp.entity.Question;
import com.oleksandr.doroshchuk.quizapp.service.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping(path = "question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }
    
    @PostMapping("category")
    public ResponseEntity<Question> postMethodName(@RequestBody Question question) {
        return questionService.save(question);
    }


    @ExceptionHandler(Exception.class) 
    public ResponseEntity<String> handleException(Exception e) { 
        System.out.println(e); 
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    
    
}
