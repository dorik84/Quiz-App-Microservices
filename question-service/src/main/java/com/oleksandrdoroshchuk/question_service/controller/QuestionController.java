package com.oleksandrdoroshchuk.question_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.oleksandrdoroshchuk.question_service.entity.Question;
import com.oleksandrdoroshchuk.question_service.model.QuestionStripper;
import com.oleksandrdoroshchuk.question_service.model.Response;
import com.oleksandrdoroshchuk.question_service.service.QuestionService;

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
import org.springframework.web.bind.annotation.RequestParam;





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
    public ResponseEntity<Question> saveQuestion (@RequestBody Question question) {
        return questionService.saveOne(question);
    }

    @PostMapping(path="categories")
    public ResponseEntity<List<Question>> saveQuestions(@RequestBody List<Question> questions) {
        return questionService.saveMany(questions);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuizQuestions(@RequestParam String category, @RequestParam Integer numQuestions ) {
        return questionService.getQuizQuestions(category,numQuestions);
    }

    @GetMapping("getQuestions")
    public ResponseEntity<List<QuestionStripper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        return questionService.getQuestionsFromId(questionIds);
    }
    
    
    @PostMapping("getScore")
    public ResponseEntity<Integer> gerScore(@RequestBody List<Response> responses) {
        return questionService.getScore(responses);
    }
    

    @ExceptionHandler(Exception.class) 
    public ResponseEntity<String> handleException(Exception e) { 
        System.out.println(e); 
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    
    

}
