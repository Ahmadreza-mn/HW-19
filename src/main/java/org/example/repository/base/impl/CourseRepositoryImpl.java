package org.example.repository.base.impl;

import org.example.model.*;
import org.example.repository.base.CourseRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CourseRepositoryImpl implements CourseRepository {

    private final Session session;

    public CourseRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void save(Course course) {
        Transaction tx = session.beginTransaction();
        session.save(course);
        tx.commit();
    }

    @Override
    public void update(Course course) {
        Transaction tx = session.beginTransaction();
        session.update(course);
        tx.commit();
    }

    @Override
    public void delete(Course course) {
        Transaction tx = session.beginTransaction();
        session.delete(course);
        tx.commit();
    }

    @Override
    public Course findById(Long courseId) {
        return session.get(Course.class, courseId);
    }

    @Override
    public List<Course> findAll() {
        return session.createQuery("FROM Course", Course.class).list();
    }

    @Override
    public void addStudentToCourse(Long courseId, Long studentId) {
        Transaction tx = session.beginTransaction();

        Course course = session.get(Course.class, courseId);
        Student student = session.get(Student.class, studentId);

        if (course != null && student != null) {
            course.getStudents().add(student);
            session.update(course);
        }

        tx.commit();
    }

    @Override
    public void removeStudentFromCourse(Long courseId, Long studentId) {
        Transaction tx = session.beginTransaction();

        Course course = session.get(Course.class, courseId);
        Student student = session.get(Student.class, studentId);

        if (course != null && student != null) {
            course.getStudents().remove(student);
            session.update(course);
        }

        tx.commit();
    }

    @Override
    public void addMasterToCourse(Long courseId, Long masterId) {
        Transaction tx = session.beginTransaction();

        Course course = session.get(Course.class, courseId);
        Master master = session.get(Master.class, masterId);

        if (course != null && master != null) {
            course.setMaster(master);
            session.update(course);
        }

        tx.commit();
    }

    @Override
    public List<MultipleChoiceQuestion> getMultipleChoiceQuestionsByCourse(Long courseId) {
        String hql = "FROM MultipleChoiceQuestion q WHERE q.course.id = :courseId";
        Query<MultipleChoiceQuestion> query = session.createQuery(hql, MultipleChoiceQuestion.class);
        query.setParameter("courseId", courseId);
        return query.list();
    }

    @Override
    public List<DescriptiveQuestion> getDescriptiveQuestionsByCourse(Long courseId) {
        String hql = "FROM DescriptiveQuestion q WHERE q.course.id = :courseId";
        Query<DescriptiveQuestion> query = session.createQuery(hql, DescriptiveQuestion.class);
        query.setParameter("courseId", courseId);
        return query.list();
    }

    @Override
    public void deleteQuestionFromCourseBank(Long courseId, Long questionId, String questionType) {
        Transaction tx = session.beginTransaction();

        if ("MCQ".equalsIgnoreCase(questionType)) {
            MultipleChoiceQuestion question = session.get(MultipleChoiceQuestion.class, questionId);
            if (question != null && question.getCourse().getId().equals(courseId)) {
                session.delete(question);
            }
        } else if ("Descriptive".equalsIgnoreCase(questionType)) {
            DescriptiveQuestion question = session.get(DescriptiveQuestion.class, questionId);
            if (question != null && question.getCourse().getId().equals(courseId)) {
                session.delete(question);
            }
        }

        tx.commit();
    }
}