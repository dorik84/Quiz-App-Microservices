package com.oleksandrdoroshchuk.question_service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oleksandrdoroshchuk.question_service.dao.QuestionDao;
import com.oleksandrdoroshchuk.question_service.entity.Question;
import com.oleksandrdoroshchuk.question_service.model.QuestionStripper;
import com.oleksandrdoroshchuk.question_service.model.Response;



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

    public ResponseEntity<Question> saveOne(Question question) {
        return new ResponseEntity<>(questionDao.save(question),HttpStatus.CREATED);
    }

    public ResponseEntity<List<Question>> saveMany(List<Question> questions) {
        return new ResponseEntity<>(questionDao.saveAll(questions),HttpStatus.CREATED);
    }

    public ResponseEntity<List<Integer>> getQuizQuestions(String category, Integer numQuestions) {
        List<Integer> questionIds = questionDao.findRandomQuestionsByCategory(category,numQuestions);
        return new ResponseEntity<>(questionIds, HttpStatus.OK);
        
    }

    public ResponseEntity<List<QuestionStripper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionStripper> qws = questionIds.stream()
            .map(id -> questionDao.findById(id))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(QuestionStripper::new)
            .collect(Collectors.toList());
        return new ResponseEntity<List<QuestionStripper>>(qws, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        Integer score=0;

        for (Response res : responses){
            String rightAnswer = questionDao.findById(res.getId()).get().getAnswer();
            if (res.getResponse().equals(rightAnswer)){
                score++;
            }
        }
        return new ResponseEntity<Integer>(score,HttpStatus.OK);
    }


}
