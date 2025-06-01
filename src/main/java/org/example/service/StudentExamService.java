package org.example.service;

import org.example.dto.StudentExamDto;
import org.example.model.Exam;
import org.example.model.StudentExam;

import java.util.List;
import java.util.Optional;

public interface StudentExamService {
    void startExam(StudentExamDto dto);

    void submitExam(Long studentId, Long examId);

    Optional<StudentExam> findStudentExam(Long studentId, Long examId);

    List<Exam> findCompletedExamsOfStudent(Long studentId);

}
