package org.example.service.impl;

import org.example.dto.ExamDto;
import org.example.model.DescriptiveQuestion;
import org.example.model.Exam;
import org.example.model.MultipleChoiceQuestion;
import org.example.repository.ExamRepository;
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
    public boolean deleteExam(Long id) {
        return false;
    }

    @Override
    public List<MultipleChoiceQuestion> multipleChoiceQuestionsOfExam(Long examId) {
        return List.of();
    }

    @Override
    public List<DescriptiveQuestion> descriptiveQuestionsOfExam(Long examId) {
        return List.of();
    }
}
