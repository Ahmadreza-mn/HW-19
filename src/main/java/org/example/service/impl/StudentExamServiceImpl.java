package org.example.service.impl;

import jakarta.persistence.EntityManager;
import org.example.dto.StudentExamDto;
import org.example.model.Exam;
import org.example.model.Student;
import org.example.model.StudentExam;
import org.example.repository.base.StudentExamRepository;
import org.example.service.StudentExamService;

import java.util.List;
import java.util.Optional;

public class StudentExamServiceImpl implements StudentExamService {

    private final StudentExamRepository studentExamRepository;
    private final EntityManager entityManager;

    public StudentExamServiceImpl(StudentExamRepository studentExamRepository, EntityManager entityManager) {
        this.studentExamRepository = studentExamRepository;
        this.entityManager = entityManager;
    }

    @Override
    public void startExam(StudentExamDto dto) {
        entityManager.getTransaction().begin();

        Student student = entityManager.find(Student.class, dto.getStudentId());
        Exam exam = entityManager.find(Exam.class, dto.getExamId());

        StudentExam studentExam = new StudentExam();
        studentExam.setStudent(student);
        studentExam.setExam(exam);
        studentExam.setStartTime(dto.getStartTime());
        studentExam.setSubmitted(false);

        studentExamRepository.save(studentExam);

        entityManager.getTransaction().commit();
    }

    @Override
    public void submitExam(Long studentId, Long examId) {
        entityManager.getTransaction().begin();

        Optional<StudentExam> optional = studentExamRepository.findByStudentAndExam(studentId, examId);
        if (optional.isEmpty()) {
            throw new RuntimeException("StudentExam not found");
        }

        StudentExam studentExam = optional.get();
        studentExam.setSubmitted(true);
        studentExam.setEndTime(java.time.LocalDateTime.now());

        studentExamRepository.update(studentExam);

        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<StudentExam> findStudentExam(Long studentId, Long examId) {
        return studentExamRepository.findByStudentAndExam(studentId, examId);
    }

    @Override
    public List<Exam> findCompletedExamsOfStudent(Long studentId) {
        return studentExamRepository.findCompletedExamsByStudentId(studentId);
    }
}