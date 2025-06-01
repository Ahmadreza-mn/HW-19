package org.example.repository;

import org.example.model.Master;
import org.example.repository.base.BaseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MasterRepository extends BaseRepository<Master, Long> {
    public MasterRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Master.class);
    }
}