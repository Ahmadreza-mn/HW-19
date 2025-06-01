package org.example.service;

import org.example.dto.questiondto.*;
import org.example.model.DescriptiveQuestion;
import org.example.model.MultipleChoiceQuestion;
import org.example.model.Question;
import org.example.model.QuestionExam;

import java.util.List;

public interface QuestionService {
    void createMultipleChoiceQuestion(Long courseId, Long examId, MultipleChoiceQuestionDto multipleChoiceQuestionDto);

    void createDescriptiveQuestion(Long courseId, Long examId, DescriptiveQuestionDto dto);

    void addQuestionToExam(AddQuestionToExamDto dto);

    void deleteQuestionFromExam(DeleteQuestionFromExamDto dto);

    void createMultipleChoiceQuestionForBank(Long courseId, MultipleChoiceQuestionDto multipleChoiceQuestionDto);

    void createDescriptiveQuestionForBank(Long courseId, DescriptiveQuestionDto dto);

    void updateMultipleChoiceQuestion(UpdateMCQDto dto);

    void updateDescriptiveQuestion(UpdateDQBankDto dto);

    MultipleChoiceQuestion findMCQById(Long questionId);

    DescriptiveQuestion findDQById(Long questionId);

    List<Question> deletedQuestions();

    void deleteDeletedQuestionFromBank(Long questionId);

    void addDeletedQuestionFromBankToBank(Long questionId, Long courseId);

    Question findById(Long id);

    List<QuestionExam> findQuestionExamByExam(Long examId);
}
