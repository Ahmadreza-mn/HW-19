package org.example.service.impl;

import org.example.dto.ExamDto;
import org.example.model.DescriptiveQuestion;
import org.example.model.Exam;
import org.example.model.MultipleChoiceQuestion;
import org.example.repository.base.ExamRepository;
import org.example.service.ExamService;

import java.util.List;

public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public void addExamToCourse(Long courseId, Long masterId, ExamDto examDto) {

    }

    @Override
    public boolean updateExam(Long id, ExamDto examDto) {
        return false;
    }

    @Override
    public Exam findById(Long id) {
        return null;
    }

    @Override
    public void addExamToCourse(Long courseId, Long masterId, Exam exam) {
        examRepository.addExamToCourse(courseId, masterId, exam);
    }

    @Override
    public boolean updateExam(Exam exam) {
        return examRepository.updateExam(exam);
    }

    @Override
    public Exam findExamById(Long id) {
        return examRepository.findById(id);
    }

    @Override
    public boolean deleteExam(Long id) {
        return examRepository.deleteExam(id);
    }

    @Override
    public List<MultipleChoiceQuestion> multipleChoiceQuestionsOfExam(Long examId) {
        return List.of();
    }

    @Override
    public List<DescriptiveQuestion> descriptiveQuestionsOfExam(Long examId) {
        return List.of();
    }

    @Override
    public List<MultipleChoiceQuestion> getMultipleChoiceQuestionsByExam(Long examId) {
        return examRepository.getMultipleChoiceQuestionsByExam(examId);
    }

    @Override
    public List<DescriptiveQuestion> getDescriptiveQuestionsByExam(Long examId) {
        return examRepository.getDescriptiveQuestionsByExam(examId);
    }
}
