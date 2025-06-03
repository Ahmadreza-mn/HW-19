package org.example.repository.base.impl;

import org.example.model.*;
import org.example.repository.base.ExamRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ExamRepositoryImpl implements ExamRepository {

    private final Session session;

    public ExamRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void addExamToCourse(Long courseId, Long masterId, Exam exam) {
        Transaction tx = session.beginTransaction();

        Course course = session.get(Course.class, courseId);
        Master master = session.get(Master.class, masterId);

        if (course != null && master != null) {
            exam.setCourse(course);
            exam.setMaster(master);
            session.save(exam);
        }
        tx.commit();
    }

    @Override
    public boolean updateExam(Exam exam) {
        Transaction tx = session.beginTransaction();
        try {
            session.update(exam);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    @Override
    public Exam findById(Long id) {
        return session.get(Exam.class, id);
    }

    @Override
    public boolean deleteExam(Long id) {
        Transaction tx = session.beginTransaction();
        Exam exam = session.get(Exam.class, id);
        if (exam != null) {
            session.delete(exam);
            tx.commit();
            return true;
        }
        tx.rollback();
        return false;
    }

    @Override
    public List<MultipleChoiceQuestion> getMultipleChoiceQuestionsByExam(Long examId) {
        String hql = "FROM MultipleChoiceQuestion q WHERE q.exam.id = :examId";
        Query<MultipleChoiceQuestion> query = session.createQuery(hql, MultipleChoiceQuestion.class);
        query.setParameter("examId", examId);
        return query.list();
    }

    @Override
    public List<DescriptiveQuestion> getDescriptiveQuestionsByExam(Long examId) {
        String hql = "FROM DescriptiveQuestion q WHERE q.exam.id = :examId";
        Query<DescriptiveQuestion> query = session.createQuery(hql, DescriptiveQuestion.class);
        query.setParameter("examId", examId);
        return query.list();
    }
}