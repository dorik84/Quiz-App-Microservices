package com.oleksandr.doroshchuk.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oleksandr.doroshchuk.quizapp.Question;
import com.oleksandr.doroshchuk.quizapp.dao.QuestionDao;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionDao.findAll(),HttpStatus.FOUND);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        return new ResponseEntity<>(questionDao.findAllByCategory(category),HttpStatus.FOUND);
       
    }

    public ResponseEntity<Question> save(Question question) {
        return new ResponseEntity<>(questionDao.save(question),HttpStatus.CREATED);
    }
}
