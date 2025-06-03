package org.example.service.impl;

import org.example.dto.answerdto.DescriptiveAnswerDto;
import org.example.dto.answerdto.MultipleChoiceAnswerDto;
import org.example.model.DescriptiveAnswer;
import org.example.model.MultipleChoiceAnswer;
import org.example.repository.base.AnswerRepository;
import org.example.service.AnswerService;

import java.util.Map;

 public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public void saveMultipleChoiceAnswer(MultipleChoiceAnswerDto dto) {

    }

    @Override
    public void saveDescriptiveAnswer(DescriptiveAnswerDto dto) {

    }

    @Override
    public void saveMultipleChoiceAnswer(MultipleChoiceAnswer answer) {
        answerRepository.saveMultipleChoiceAnswer(answer);
    }

    @Override
    public void saveDescriptiveAnswer(DescriptiveAnswer answer) {
        answerRepository.saveDescriptiveAnswer(answer);
    }

    @Override
    public Map<Long, Object> getStudentAnswers(Long studentId, Long examId) {
        return answerRepository.getStudentAnswers(studentId, examId);
    }
}