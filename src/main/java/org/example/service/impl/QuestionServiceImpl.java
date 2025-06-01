package org.example.service.impl;

import org.example.dto.questiondto.*;
import org.example.model.DescriptiveQuestion;
import org.example.model.MultipleChoiceQuestion;
import org.example.model.Question;
import org.example.model.QuestionExam;
import org.example.repository.QuestionRepository;
import org.example.service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void createMultipleChoiceQuestion(Long courseId, Long examId, MultipleChoiceQuestionDto multipleChoiceQuestionDto) {

    }

    @Override
    public void createDescriptiveQuestion(Long courseId, Long examId, DescriptiveQuestionDto dto) {

    }

    @Override
    public void addQuestionToExam(AddQuestionToExamDto dto) {

    }

    @Override
    public void deleteQuestionFromExam(DeleteQuestionFromExamDto dto) {

    }

    @Override
    public void createMultipleChoiceQuestionForBank(Long courseId, MultipleChoiceQuestionDto multipleChoiceQuestionDto) {

    }

    @Override
    public void createDescriptiveQuestionForBank(Long courseId, DescriptiveQuestionDto dto) {

    }

    @Override
    public void updateMultipleChoiceQuestion(UpdateMCQDto dto) {

    }

    @Override
    public void updateDescriptiveQuestion(UpdateDQBankDto dto) {

    }

    @Override
    public MultipleChoiceQuestion findMCQById(Long questionId) {
        return null;
    }

    @Override
    public DescriptiveQuestion findDQById(Long questionId) {
        return null;
    }

    @Override
    public List<Question> deletedQuestions() {
        return List.of();
    }

    @Override
    public void deleteDeletedQuestionFromBank(Long questionId) {

    }

    @Override
    public void addDeletedQuestionFromBankToBank(Long questionId, Long courseId) {

    }

    @Override
    public Question findById(Long id) {
        return null;
    }

    @Override
    public List<QuestionExam> findQuestionExamByExam(Long examId) {
        return List.of();
    }
}
