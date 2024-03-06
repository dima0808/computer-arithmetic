package com.civka.calculatordemo.service;

import com.civka.calculatordemo.dao.QuestionDataRepository;
import com.civka.calculatordemo.entity.QuestionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionDataServiceImpl implements QuestionDataService {

    private final QuestionDataRepository questionDataRepository;

    @Autowired
    public QuestionDataServiceImpl(QuestionDataRepository questionDataRepository) {
        this.questionDataRepository = questionDataRepository;
    }

    @Override
    public List<QuestionData> findAll() {
        return questionDataRepository.findAllByOrderByQuestionDateDesc();
    }

    @Override
    public List<QuestionData> findByUsername(String username) {
        return questionDataRepository.findByUsername(username);
    }

    @Override
    public QuestionData findById(Integer id) {
        Optional<QuestionData> questionData = questionDataRepository.findById(id);
        return questionData.orElse(null);
    }

    @Override
    public QuestionData saveQuestionData(QuestionData questionData) {
        return questionDataRepository.save(questionData);
    }

    @Override
    public void deleteQuestionDataById(Integer id) {
        questionDataRepository.deleteById(id);
    }
}
