package org.example.service.impl;

import org.example.dto.StudentDto;
import org.example.model.Course;
import org.example.model.Student;
import org.example.repository.StudentRepository;
import org.example.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void saveStudent(StudentDto studentDto) {

    }

    @Override
    public boolean updateStudent(Long id, StudentDto studentDto) {
        return false;
    }

    @Override
    public boolean checkPassword(Student student, String oldPassword) {
        return false;
    }

    @Override
    public void changePassword(Student student, String newPassword) {

    }

    @Override
    public Student findById(Long id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public List<Course> coursesOfStudent(Long studentId) {
        return List.of();
    }

    @Override
    public Student findByUsername(String username) {
        return null;
    }
}
