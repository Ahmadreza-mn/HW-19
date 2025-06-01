package org.example.repository;

import org.example.model.Exam;
import org.example.repository.base.BaseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ExamRepository extends BaseRepository<Exam, Long> {
    public ExamRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Exam.class);
    }
}