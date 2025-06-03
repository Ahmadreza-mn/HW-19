package org.example.service.impl;

import org.example.repository.base.GradingRepository;
import org.example.service.GradingService;

import java.util.Map;

public class GradingServiceImpl implements GradingService {

    private final GradingRepository gradingRepository;

    public GradingServiceImpl(GradingRepository gradingRepository) {
        this.gradingRepository = gradingRepository;
    }

    @Override
    public void autoGradeMultipleChoice(Long examId, Long questionId) {
        gradingRepository.autoGradeMultipleChoice(examId, questionId);
    }

    @Override
    public void gradeDescriptiveAnswer(Long questionId, Long examId, Double score) {
        gradingRepository.gradeDescriptiveAnswer(questionId, examId, score);
    }

    @Override
    public Map<Long, Double> getAnswerGrades(Long studentId, Long examId) {
        return gradingRepository.getAnswerGrades(studentId, examId);
    }

    @Override
    public Double getMaximumScoreForAnswer(Long examId, Long questionId) {
        return gradingRepository.getMaximumScoreForAnswer(examId, questionId);
    }

    @Override
    public void setTotalScoreForStudentExam(Long studentId, Long examId) {
        gradingRepository.setTotalScoreForStudentExam(studentId, examId);
    }

    @Override
    public Double getStudentScoreInExam(Long studentId, Long examId) {
        return gradingRepository.getStudentScoreInExam(studentId, examId);
    }
}