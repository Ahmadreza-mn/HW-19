package org.example.service.impl;

import org.example.dto.StudentExamDto;
import org.example.model.Exam;
import org.example.model.StudentExam;
import org.example.service.StudentExamService;

import java.util.List;
import java.util.Optional;

public class StudentExamServiceImpl implements StudentExamService {
    private final StudentExamRepository studentExamRepository;
    @Override
    public void startExam(StudentExamDto dto) {

    }

    @Override
    public void submitExam(Long studentId, Long examId) {

    }

    @Override
    public Optional<StudentExam> findStudentExam(Long studentId, Long examId) {
        return Optional.empty();
    }

    @Override
    public List<Exam> findCompletedExamsOfStudent(Long studentId) {
        return List.of();
    }
}
