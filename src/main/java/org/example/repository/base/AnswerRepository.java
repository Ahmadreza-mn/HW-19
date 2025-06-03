package org.example.repository.base;

import org.example.model.DescriptiveAnswer;
import org.example.model.MultipleChoiceAnswer;

import java.util.Map;

public interface AnswerRepository {

    void saveMultipleChoiceAnswer(MultipleChoiceAnswer entity);

    void saveDescriptiveAnswer(DescriptiveAnswer entity);

    Map<Long, Object> getStudentAnswers(Long studentId, Long examId);
}
