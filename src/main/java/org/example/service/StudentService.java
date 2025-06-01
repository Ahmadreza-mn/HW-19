package org.example.service;

import org.example.dto.StudentDto;
import org.example.model.Course;
import org.example.model.Student;

import java.util.List;

public interface StudentService {
    void saveStudent(StudentDto studentDto);

    boolean updateStudent(Long id, StudentDto studentDto);

    boolean checkPassword(Student student, String oldPassword);

    void changePassword(Student student, String newPassword);

    Student findById(Long id);

    List<Student> findAll();

    List<Course> coursesOfStudent(Long studentId);

    Student findByUsername(String username);

}
