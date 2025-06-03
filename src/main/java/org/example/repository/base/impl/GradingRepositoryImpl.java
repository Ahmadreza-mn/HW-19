package org.example.repository.base.impl;

import org.example.model.*;
import org.example.repository.base.GradingRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradingRepositoryImpl implements GradingRepository {

    private final Session session;

    public GradingRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void autoGradeMultipleChoice(Long examId, Long questionId) {
        Transaction tx = session.beginTransaction();

        // Fetch correct option
        String correctHql = """
            SELECT o FROM Option o 
            WHERE o.question.id = :questionId AND o.correct = true
        """;
        Option correctOption = session.createQuery(correctHql, Option.class)
                .setParameter("questionId", questionId)
                .setMaxResults(1)
                .uniqueResult();

        if (correctOption != null) {
            String hqlAnswers = """
                FROM MultipleChoiceAnswer a 
                WHERE a.question.id = :questionId AND a.exam.id = :examId
            """;
            List<MultipleChoiceAnswer> answers = session.createQuery(hqlAnswers, MultipleChoiceAnswer.class)
                    .setParameter("questionId", questionId)
                    .setParameter("examId", examId)
                    .list();

            for (MultipleChoiceAnswer answer : answers) {
                boolean isCorrect = answer.getSelectedOption() != null &&
                        answer.getSelectedOption().getId().equals(correctOption.getId());
                double score = isCorrect ? answer.getQuestion().getScore() : 0.0;
                answer.setScore(score);
                session.merge(answer);
            }
        }

        tx.commit();
    }

    @Override
    public void gradeDescriptiveAnswer(Long questionId, Long examId, Double score) {
        Transaction tx = session.beginTransaction();

        String hql = """
            FROM DescriptiveAnswer a 
            WHERE a.question.id = :questionId AND a.exam.id = :examId
        """;
        List<DescriptiveAnswer> answers = session.createQuery(hql, DescriptiveAnswer.class)
                .setParameter("questionId", questionId)
                .setParameter("examId", examId)
                .list();

        for (DescriptiveAnswer answer : answers) {
            answer.setScore(score);
            session.merge(answer);
        }

        tx.commit();
    }

    @Override
    public Map<Long, Double> getAnswerGrades(Long studentId, Long examId) {
        Map<Long, Double> grades = new HashMap<>();

        // MCQ grades
        String mcqHql = """
            SELECT a.question.id, a.score 
            FROM MultipleChoiceAnswer a 
            WHERE a.student.id = :studentId AND a.exam.id = :examId
        """;
        List<Object[]> mcqResults = session.createQuery(mcqHql, Object[].class)
                .setParameter("studentId", studentId)
                .setParameter("examId", examId)
                .list();

        for (Object[] row : mcqResults) {
            grades.put((Long) row[0], (Double) row[1]);
        }

        // Descriptive grades
        String dqHql = """
            SELECT a.question.id, a.score 
            FROM DescriptiveAnswer a 
            WHERE a.student.id = :studentId AND a.exam.id = :examId
        """;
        List<Object[]> dqResults = session.createQuery(dqHql, Object[].class)
                .setParameter("studentId", studentId)
                .setParameter("examId", examId)
                .list();

        for (Object[] row : dqResults) {
            grades.put((Long) row[0], (Double) row[1]);
        }

        return grades;
    }

    @Override
    public Double getMaximumScoreForAnswer(Long examId, Long questionId) {
        String hql = """
            SELECT q.score 
            FROM MultipleChoiceQuestion q 
            WHERE q.id = :questionId AND q.exam.id = :examId
        """;
        return session.createQuery(hql, Double.class)
                .setParameter("questionId", questionId)
                .setParameter("examId", examId)
                .uniqueResult();
    }

    @Override
    public void setTotalScoreForStudentExam(Long studentId, Long examId) {
        Transaction tx = session.beginTransaction();

        String mcqScoreHql = """
            SELECT COALESCE(SUM(a.score), 0) 
            FROM MultipleChoiceAnswer a 
            WHERE a.student.id = :studentId AND a.exam.id = :examId
        """;
        double mcqTotal = session.createQuery(mcqScoreHql, Double.class)
                .setParameter("studentId", studentId)
                .setParameter("examId", examId)
                .uniqueResult();

        String dqScoreHql = """
            SELECT COALESCE(SUM(a.score), 0) 
            FROM DescriptiveAnswer a 
            WHERE a.student.id = :studentId AND a.exam.id = :examId
        """;
        double dqTotal = session.createQuery(dqScoreHql, Double.class)
                .setParameter("studentId", studentId)
                .setParameter("examId", examId)
                .uniqueResult();

        double total = mcqTotal + dqTotal;

        String findExamHql = """
            FROM StudentExam se 
            WHERE se.student.id = :studentId AND se.exam.id = :examId
        """;
        StudentExam se = session.createQuery(findExamHql, StudentExam.class)
                .setParameter("studentId", studentId)
                .setParameter("examId", examId)
                .uniqueResult();

        if (se != null) {
            se.setTotalScore(total);
            session.merge(se);
        }

        tx.commit();
    }

    @Override
    public Double getStudentScoreInExam(Long studentId, Long examId) {
        String hql = """
            SELECT se.totalScore 
            FROM StudentExam se 
            WHERE se.student.id = :studentId AND se.exam.id = :examId
        """;
        return session.createQuery(hql, Double.class)
                .setParameter("studentId", studentId)
                .setParameter("examId", examId)
                .uniqueResult();
    }
}