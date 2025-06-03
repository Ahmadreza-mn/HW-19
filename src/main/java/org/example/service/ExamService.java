package org.example.service;

import org.example.dto.ExamDto;
import org.example.model.DescriptiveQuestion;
import org.example.model.Exam;
import org.example.model.MultipleChoiceQuestion;

import java.util.List;

public interface ExamService {
    void addExamToCourse(Long courseId, Long masterId, ExamDto examDto);

    boolean updateExam(Long id, ExamDto examDto);

    Exam findById(Long id);

    void addExamToCourse(Long courseId, Long masterId, Exam exam);

    boolean updateExam(Exam exam);

    Exam findExamById(Long id);

    boolean deleteExam(Long id);

    List<MultipleChoiceQuestion> multipleChoiceQuestionsOfExam(Long examId);

    List<DescriptiveQuestion> descriptiveQuestionsOfExam(Long examId);

    List<MultipleChoiceQuestion> getMultipleChoiceQuestionsByExam(Long examId);

    List<DescriptiveQuestion> getDescriptiveQuestionsByExam(Long examId);
}