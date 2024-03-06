package com.civka.calculatordemo.service;

import com.civka.calculatordemo.entity.QuestionData;

import java.util.List;

public interface QuestionDataService {

    List<QuestionData> findAll();

    List<QuestionData> findByUsername(String username);

    QuestionData findById(Integer id);

    QuestionData saveQuestionData(QuestionData questionData);

    void deleteQuestionDataById(Integer id);
}
