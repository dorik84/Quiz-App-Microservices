package com.oleksandr.doroshchuk.quizapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oleksandr.doroshchuk.quizapp.entity.Quiz;
import com.oleksandr.doroshchuk.quizapp.model.QuestionWrapper;
import com.oleksandr.doroshchuk.quizapp.model.Response;
import com.oleksandr.doroshchuk.quizapp.service.QuizService;

import jakarta.websocket.server.PathParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam Integer numQs, @RequestParam String title ) {
        // return new ResponseEntity<>("Hello", HttpStatus.OK);
        return quizService.createQuiz(category,numQs,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByQuizId(@PathVariable Integer id) {
        return quizService.getQuestionsByQuizId(id);
    }
    
    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> submitQuizAnswers(@PathVariable Integer quizId, @RequestBody List<Response> responses) {
        System.out.println("submitQuizAnswers triggered");
        return quizService.calculateScore(quizId,responses);
    }
    
    
}
