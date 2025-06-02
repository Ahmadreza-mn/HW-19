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
    public void autoMultipleChoiceGrading(Long examId, Long questionId) {

    }

    @Override
    public void gradingDescriptiveAnswer(Long questionId, Long examId, Double score) {

    }

    @Override
    public Map<Long, Double> getAnswerGrades(Long studentId, Long examId) {
        return Map.of();
    }

    @Override
    public Double getMaximumScoreForAnswer(Long examId, Long questionId) {
        return 0.0;
    }

    @Override
    public void setTotalScoreOfExamForStudent(Long studentId, Long examId) {

    }

    @Override
    public Double getScoreOfStudentInExam(Long studentId, Long examId) {
        return 0.0;
    }
}