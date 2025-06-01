package org.example.service.impl;

import org.example.dto.answerdto.DescriptiveAnswerDto;
import org.example.dto.answerdto.MultipleChoiceAnswerDto;
import org.example.repository.AnswerRepository;
import org.example.service.AnswerService;

import java.util.Map;

public class AnswerServiceImpl implements AnswerService {

    public AnswerServiceImpl(AnswerRepository answerRepository) {
    }

    @Override
    public void saveMultipleChoiceAnswer(MultipleChoiceAnswerDto dto) {

    }

    @Override
    public void saveDescriptiveAnswer(DescriptiveAnswerDto dto) {

    }

    @Override
    public Map<Long, Object> getStudentAnswers(Long studentId, Long examId) {
        return Map.of();
    }
}
