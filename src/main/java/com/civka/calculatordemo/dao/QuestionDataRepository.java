package com.civka.calculatordemo.dao;

import com.civka.calculatordemo.entity.QuestionData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionDataRepository extends JpaRepository<QuestionData, Integer> {

    List<QuestionData> findAllByOrderByQuestionDateDesc();
}
