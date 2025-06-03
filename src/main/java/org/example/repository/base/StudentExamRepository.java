package org.example.repository.base;

import org.example.model.Exam;
import org.example.model.StudentExam;

import java.util.List;
import java.util.Optional;

public interface StudentExamRepository {
    void save(StudentExam studentExam);
    Optional<StudentExam> findByStudentAndExam(Long studentId, Long examId);
    List<Exam> findCompletedExamsByStudentId(Long studentId);
    void update(StudentExam studentExam);
}
