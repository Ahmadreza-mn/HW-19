package org.example.repository.base.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.model.*;
import org.example.repository.base.QuestionRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class QuestionRepositoryImpl implements QuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveMCQ(MultipleChoiceQuestion question) {
        entityManager.unwrap(Session.class).persist(question);
    }

    @Override
    public void saveDQ(DescriptiveQuestion question) {
        entityManager.unwrap(Session.class).persist(question);
    }

    @Override
    public void updateMCQ(MultipleChoiceQuestion question) {
        entityManager.unwrap(Session.class).merge(question);
    }

    @Override
    public void updateDQ(DescriptiveQuestion question) {
        entityManager.unwrap(Session.class).merge(question);
    }

    @Override
    public Optional<MultipleChoiceQuestion> findMCQById(Long id) {
        var session = entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(MultipleChoiceQuestion.class, id));
    }

    @Override
    public Optional<DescriptiveQuestion> findDQById(Long id) {
        var session = entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(DescriptiveQuestion.class, id));
    }

    @Override
    public Optional<Question> findById(Long id) {
        var session = entityManager.unwrap(Session.class);
        return Optional.ofNullable(session.get(Question.class, id));
    }

    @Override
    public List<Question> findDeletedQuestions() {
        return entityManager.unwrap(Session.class)
                .createQuery("FROM Question q WHERE q.deleted = true", Question.class)
                .list();
    }

    @Override
    public void deleteFromBank(Long questionId) {
        var session = entityManager.unwrap(Session.class);
        Question question = session.get(Question.class, questionId);
        if (question != null) {
            session.remove(question);
        }
    }

    @Override
    public void restoreDeletedQuestionToBank(Long questionId, Long courseId) {
        var session = entityManager.unwrap(Session.class);
        Question question = session.get(Question.class, questionId);
        if (question != null) {
            question.setDeleted(false);
            Course course = session.get(Course.class, courseId);
            question.setCourse(course);
            session.merge(question);
        }
    }

    @Override
    public void addQuestionToExam(Long questionId, Long examId) {
        var session = entityManager.unwrap(Session.class);
        Question question = session.get(Question.class, questionId);
        Exam exam = session.get(Exam.class, examId);
        if (question != null && exam != null) {
            QuestionExam qe = new QuestionExam();
            qe.setExam(exam);
            qe.setQuestion(question);
            session.persist(qe);
        }
    }

    @Override
    public void removeQuestionFromExam(Long questionId, Long examId) {
        var session = entityManager.unwrap(Session.class);
        List<QuestionExam> results = session.createQuery(
                        "FROM QuestionExam qe WHERE qe.exam.id = :examId AND qe.question.id = :questionId", QuestionExam.class)
                .setParameter("examId", examId)
                .setParameter("questionId", questionId)
                .list();
        for (QuestionExam qe : results) {
            session.remove(qe);
        }
    }

    @Override
    public List<QuestionExam> findQuestionExamsByExamId(Long examId) {
        return entityManager.unwrap(Session.class)
                .createQuery("FROM QuestionExam qe WHERE qe.exam.id = :examId", QuestionExam.class)
                .setParameter("examId", examId)
                .list();
    }
}