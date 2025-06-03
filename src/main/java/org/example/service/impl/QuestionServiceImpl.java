package org.example.service.impl;

import jakarta.persistence.EntityManager;
import org.example.dto.questiondto.*;
import org.example.model.*;
import org.example.repository.base.QuestionRepository;
import org.example.service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final EntityManager entityManager;

    public QuestionServiceImpl(QuestionRepository questionRepository, EntityManager entityManager) {
        this.questionRepository = questionRepository;
        this.entityManager = entityManager;
    }

    @Override
    public void createMultipleChoiceQuestion(Long courseId, Long examId, MultipleChoiceQuestionDto dto) {
        entityManager.getTransaction().begin();
        Course course = entityManager.find(Course.class, courseId);
        Exam exam = entityManager.find(Exam.class, examId);

        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        mcq.setQuestionText(dto.getText());
        mcq.setCorrectOptionIndex(dto.getCorrectOptionIndex());
        mcq.setCourse(course);

        List<Option> options = dto.getOptions().stream().map(optText -> {
            Option option = new Option();
            option.setText(String.valueOf(optText));
            option.setQuestion(mcq);
            return option;
        }).toList();
        mcq.setOptions(options);

        questionRepository.saveMCQ(mcq);
        questionRepository.addQuestionToExam(mcq.getId(), exam.getId());
        entityManager.getTransaction().commit();
    }

    @Override
    public void createDescriptiveQuestion(Long courseId, Long examId, DescriptiveQuestionDto dto) {
        entityManager.getTransaction().begin();
        Course course = entityManager.find(Course.class, courseId);
        Exam exam = entityManager.find(Exam.class, examId);

        DescriptiveQuestion dq = new DescriptiveQuestion();
        dq.setQuestionText(dto.getQuestionText());
        dq.setSampleAnswer(dto.getSampleAnswer());
        dq.setCourse(course);

        questionRepository.saveDQ(dq);
        questionRepository.addQuestionToExam(dq.getId(), exam.getId());
        entityManager.getTransaction().commit();
    }

    @Override
    public void addQuestionToExam(AddQuestionToExamDto dto) {
        entityManager.getTransaction().begin();
        questionRepository.addQuestionToExam(dto.getQuestionId(), dto.getExamId());
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteQuestionFromExam(DeleteQuestionFromExamDto dto) {
        entityManager.getTransaction().begin();
        questionRepository.removeQuestionFromExam(dto.getQuestionId(), dto.getExamId());
        entityManager.getTransaction().commit();
    }

    @Override
    public void createMultipleChoiceQuestionForBank(Long courseId, MultipleChoiceQuestionDto dto) {
        entityManager.getTransaction().begin();
        Course course = entityManager.find(Course.class, courseId);

        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
        mcq.setQuestionText(dto.getText());
        mcq.setCorrectOptionIndex(dto.getCorrectOptionIndex());
        mcq.setCourse(course);

        List<Option> options = dto.getOptions().stream().map(optText -> {
            Option option = new Option();
            option.setText(String.valueOf(optText));
            option.setQuestion(mcq);
            return option;
        }).toList();
        mcq.setOptions(options);

        questionRepository.saveMCQ(mcq);
        entityManager.getTransaction().commit();
    }

    @Override
    public void createDescriptiveQuestionForBank(Long courseId, DescriptiveQuestionDto dto) {
        entityManager.getTransaction().begin();
        Course course = entityManager.find(Course.class, courseId);

        DescriptiveQuestion dq = new DescriptiveQuestion();
        dq.setText(dto.getText());
        dq.setSampleAnswer(dto.getSampleAnswer());
        dq.setCourse(course);

        questionRepository.saveDQ(dq);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateMultipleChoiceQuestion(UpdateMCQDto dto) {
        entityManager.getTransaction().begin();
        MultipleChoiceQuestion question = questionRepository.findMCQById(dto.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        question.setQuestionText(dto.getNewText());
        question.setCorrectOptionIndex(dto.getCorrectOptionIndex());

        question.getOptions().clear();
        List<Option> newOptions = dto.getOptions().stream().map(text -> {
            Option option = new Option();
            option.setText(String.valueOf(text));
            option.setQuestion(question);
            return option;
        }).toList();
        question.setOptions(newOptions);

        questionRepository.updateMCQ(question);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateDescriptiveQuestion(UpdateDQBankDto dto) {
        entityManager.getTransaction().begin();
        DescriptiveQuestion question = questionRepository.findDQById(dto.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        question.setQuestionText(dto.getNewText());
        question.setSampleAnswer(dto.getNewSampleAnswer());
        questionRepository.updateDQ(question);
        entityManager.getTransaction().commit();
    }

    @Override
    public MultipleChoiceQuestion findMCQById(Long questionId) {
        return questionRepository.findMCQById(questionId)
                .orElseThrow(() -> new RuntimeException("MCQ not found"));
    }

    @Override
    public DescriptiveQuestion findDQById(Long questionId) {
        return questionRepository.findDQById(questionId)
                .orElseThrow(() -> new RuntimeException("Descriptive Question not found"));
    }

    @Override
    public List<Question> deletedQuestions() {
        return questionRepository.findDeletedQuestions();
    }

    @Override
    public void deleteDeletedQuestionFromBank(Long questionId) {
        entityManager.getTransaction().begin();
        questionRepository.deleteFromBank(questionId);
        entityManager.getTransaction().commit();
    }

    @Override
    public void addDeletedQuestionFromBankToBank(Long questionId, Long courseId) {
        entityManager.getTransaction().begin();
        questionRepository.restoreDeletedQuestionToBank(questionId, courseId);
        entityManager.getTransaction().commit();
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    @Override
    public List<QuestionExam> findQuestionExamByExam(Long examId) {
        return questionRepository.findQuestionExamsByExamId(examId);
    }
}