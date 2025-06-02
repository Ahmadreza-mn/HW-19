package org.example.service.impl;

import org.example.dto.StudentDto;
import org.example.model.Course;
import org.example.model.Student;
import org.example.repository.base.StudentRepository;
import org.example.service.StudentService;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void saveStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setUsername(studentDto.getUsername());
        student.setPassword(studentDto.getPassword());
        student.setFullName(studentDto.getFullName());
        student.setEmail(studentDto.getEmail());

        studentRepository.save(student);
    }

    @Override
    public boolean updateStudent(Long id, StudentDto studentDto) {
        Optional<Student> optStudent = studentRepository.findById(id);
        if (optStudent.isEmpty()) return false;

        Student student = optStudent.get();
        student.setFullName(studentDto.getFullName());
        student.setEmail(studentDto.getEmail());

        studentRepository.update(student);
        return true;
    }

    @Override
    public boolean checkPassword(Student student, String oldPassword) {

        return student.getPassword().equals(oldPassword);
    }

    @Override
    public void changePassword(Student student, String newPassword) {

        student.setPassword(newPassword);
        studentRepository.update(student);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Course> coursesOfStudent(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.map(value -> List.copyOf(value.getCourses())).orElse(List.of());
    }

    @Override
    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username).orElse(null);
    }
}
