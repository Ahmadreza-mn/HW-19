package org.example.repository.base;

import org.example.model.DescriptiveQuestion;
import org.example.model.MultipleChoiceQuestion;
import org.example.model.Question;
import org.example.model.QuestionExam;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    void saveMCQ(MultipleChoiceQuestion question);
    void saveDQ(DescriptiveQuestion question);
    void updateMCQ(MultipleChoiceQuestion question);
    void updateDQ(DescriptiveQuestion question);
    Optional<MultipleChoiceQuestion> findMCQById(Long id);
    Optional<DescriptiveQuestion> findDQById(Long id);
    Optional<Question> findById(Long id);
    List<Question> findDeletedQuestions();
    void deleteFromBank(Long questionId);
    void restoreDeletedQuestionToBank(Long questionId, Long courseId);
    void addQuestionToExam(Long questionId, Long examId);
    void removeQuestionFromExam(Long questionId, Long examId);
    List<QuestionExam> findQuestionExamsByExamId(Long examId);
}
