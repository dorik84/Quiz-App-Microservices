package com.oleksandr.doroshchuk.quizapp.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oleksandr.doroshchuk.quizapp.dao.QuestionDao;
import com.oleksandr.doroshchuk.quizapp.dao.QuizDao;
import com.oleksandr.doroshchuk.quizapp.entity.Question;
import com.oleksandr.doroshchuk.quizapp.entity.Quiz;
import com.oleksandr.doroshchuk.quizapp.model.QuestionWrapper;
import com.oleksandr.doroshchuk.quizapp.model.Response;

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


    public ResponseEntity<List<QuestionWrapper>> getQuestionsByQuizId(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> qw = questions.stream().map(q-> new QuestionWrapper(q)).collect(Collectors.toList());
        return new ResponseEntity<List<QuestionWrapper>>(qw,HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateScore(Integer quizId, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(quizId);
        List<Question> questions = quiz.get().getQuestions();
        questions.sort(Comparator.comparing(Question::getId));
        responses.sort(Comparator.comparing(Response::getId));
        Integer score=0;
     
        for (int i=0;i<responses.size();i++){
            if (responses.get(i).getResponse().equals(questions.get(i).getAnswer())){
                score++;
            }
        }
        return new ResponseEntity<Integer>(score,HttpStatus.OK);
    }

}
