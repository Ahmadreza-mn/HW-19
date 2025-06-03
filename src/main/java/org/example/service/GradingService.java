package org.example.service;

import java.util.Map;

public interface GradingService {
    void autoGradeMultipleChoice(Long examId, Long questionId);

    void gradeDescriptiveAnswer(Long questionId, Long examId, Double score);

    Map<Long, Double> getAnswerGrades(Long studentId, Long examId);

    Double getMaximumScoreForAnswer(Long examId, Long questionId);

    void setTotalScoreForStudentExam(Long studentId, Long examId);

    Double getStudentScoreInExam(Long studentId, Long examId);
}