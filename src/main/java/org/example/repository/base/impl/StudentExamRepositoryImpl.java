package org.example.repository.base.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.model.Exam;
import org.example.model.StudentExam;
import org.example.repository.base.StudentExamRepository;

import java.util.List;
import java.util.Optional;

public class StudentExamRepositoryImpl implements StudentExamRepository {

    private final EntityManager entityManager;

    public StudentExamRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(StudentExam studentExam) {
        entityManager.persist(studentExam);
    }

    @Override
    public Optional<StudentExam> findByStudentAndExam(Long studentId, Long examId) {
        TypedQuery<StudentExam> query = entityManager.createQuery(
                "SELECT se FROM StudentExam se WHERE se.student.id = :studentId AND se.exam.id = :examId",
                StudentExam.class);
        query.setParameter("studentId", studentId);
        query.setParameter("examId", examId);
        List<StudentExam> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    @Override
    public List<Exam> findCompletedExamsByStudentId(Long studentId) {
        TypedQuery<Exam> query = entityManager.createQuery(
                "SELECT se.exam FROM StudentExam se WHERE se.student.id = :studentId AND se.submitted = true",
                Exam.class);
        query.setParameter("studentId", studentId);
        return query.getResultList();
    }

    @Override
    public void update(StudentExam studentExam) {
        entityManager.merge(studentExam);
    }
}
