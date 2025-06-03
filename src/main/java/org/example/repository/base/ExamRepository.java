package org.example.repository.base;

import org.example.model.DescriptiveQuestion;
import org.example.model.Exam;
import org.example.model.MultipleChoiceQuestion;

import java.util.List;

public interface ExamRepository {

    void addExamToCourse(Long courseId, Long masterId, Exam exam);

    boolean updateExam(Exam exam);

    Exam findById(Long id);

    boolean deleteExam(Long id);

    List<MultipleChoiceQuestion> getMultipleChoiceQuestionsByExam(Long examId);

    List<DescriptiveQuestion> getDescriptiveQuestionsByExam(Long examId);
}
