
package com.oleksandr.doroshchuk.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oleksandr.doroshchuk.quizapp.entity.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer>{

}
