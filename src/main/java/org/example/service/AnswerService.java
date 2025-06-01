package org.example.service;

import org.example.dto.answerdto.DescriptiveAnswerDto;
import org.example.dto.answerdto.MultipleChoiceAnswerDto;
import org.example.model.Answer;

import java.util.List;
import java.util.Map;

public interface AnswerService {
 

    void saveMultipleChoiceAnswer(MultipleChoiceAnswerDto dto);

    void saveDescriptiveAnswer(DescriptiveAnswerDto dto);

    Map<Long, Object> getStudentAnswers(Long studentId, Long examId);

}