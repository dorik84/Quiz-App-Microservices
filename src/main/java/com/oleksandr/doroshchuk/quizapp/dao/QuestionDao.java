package com.oleksandr.doroshchuk.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oleksandr.doroshchuk.quizapp.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer>{

    List<Question> findAllByCategory(String category);

    
}
