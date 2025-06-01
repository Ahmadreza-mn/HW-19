package org.example.repository;

import org.example.model.Answer;
import org.example.repository.base.BaseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AnswerRepository extends BaseRepository<Answer, Long> {
    public AnswerRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Answer.class);
    }
}