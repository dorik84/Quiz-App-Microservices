package com.oleksandr.doroshchuk.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oleksandr.doroshchuk.quizapp.entity.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer>{

    List<Question> findAllByCategory(String category);

    @Query(value="SELECT * FROM question q WHERE q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") Integer numQs);

    // @Query(value="SELECT ALL FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ")
    // List<Question> findRandomQuestionsByCategory(String category, Integer numQs);

    
}
