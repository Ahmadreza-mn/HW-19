package org.example.service;

import org.example.dto.answerdto.DescriptiveAnswerDto;
import org.example.dto.answerdto.MultipleChoiceAnswerDto;
import org.example.model.Answer;
import org.example.model.DescriptiveAnswer;
import org.example.model.MultipleChoiceAnswer;

import java.util.List;
import java.util.Map;

public interface AnswerService {
 

    void saveMultipleChoiceAnswer(MultipleChoiceAnswerDto dto);

    void saveDescriptiveAnswer(DescriptiveAnswerDto dto);

    void saveMultipleChoiceAnswer(MultipleChoiceAnswer answer);

    void saveDescriptiveAnswer(DescriptiveAnswer answer);

    Map<Long, Object> getStudentAnswers(Long studentId, Long examId);

}