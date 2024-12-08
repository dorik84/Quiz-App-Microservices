package com.oleksandr.doroshchuk.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oleksandr.doroshchuk.quizapp.dao.QuizDao;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    public ResponseEntity<String> createQuiz(String category, Integer numQs) {
        return QuizDao.saveQuestion(new Qus)
    }

}
