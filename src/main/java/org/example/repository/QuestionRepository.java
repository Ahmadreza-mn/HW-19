package org.example.repository;

import org.example.model.Question;
import org.example.repository.base.BaseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class QuestionRepository extends BaseRepository<Question, Long> {
    public QuestionRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Question.class);
    }
}
