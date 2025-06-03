package org.example.repository.base.impl;

import org.example.model.DescriptiveAnswer;
import org.example.model.MultipleChoiceAnswer;
import org.example.repository.base.AnswerRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerRepositoryImpl implements AnswerRepository {

    private final Session session;

    public AnswerRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void saveMultipleChoiceAnswer(MultipleChoiceAnswer entity) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(entity);
        tx.commit();
    }

    @Override
    public void saveDescriptiveAnswer(DescriptiveAnswer entity) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(entity);
        tx.commit();
    }

    @Override
    public Map<Long, Object> getStudentAnswers(Long studentId, Long examId) {
        Map<Long, Object> result = new HashMap<>();


        String hqlMC = "FROM MultipleChoiceAnswer m WHERE m.student.id = :studentId AND m.exam.id = :examId";
        Query<MultipleChoiceAnswer> mcQuery = session.createQuery(hqlMC, MultipleChoiceAnswer.class);
        mcQuery.setParameter("studentId", studentId);
        mcQuery.setParameter("examId", examId);
        List<MultipleChoiceAnswer> mcAnswers = mcQuery.list();

        for (MultipleChoiceAnswer mc : mcAnswers) {
            result.put(mc.getId(), mc);
        }


        String hqlDesc = "FROM DescriptiveAnswer d WHERE d.student.id = :studentId AND d.exam.id = :examId";
        Query<DescriptiveAnswer> descQuery = session.createQuery(hqlDesc, DescriptiveAnswer.class);
        descQuery.setParameter("studentId", studentId);
        descQuery.setParameter("examId", examId);
        List<DescriptiveAnswer> descAnswers = descQuery.list();

        for (DescriptiveAnswer d : descAnswers) {
            result.put(d.getId(), d);
        }

        return result;
    }
}