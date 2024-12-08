package com.oleksandr.doroshchuk.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oleksandr.doroshchuk.quizapp.dao.QuestionDao;
import com.oleksandr.doroshchuk.quizapp.dao.QuizDao;
import com.oleksandr.doroshchuk.quizapp.entity.Question;
import com.oleksandr.doroshchuk.quizapp.entity.Quiz;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<Quiz> createQuiz(String category, Integer numQs, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQs);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        return new ResponseEntity<Quiz>(quizDao.save(quiz),HttpStatus.CREATED);
    }

}
