package org.example.service.impl;

import org.example.dto.*;
import org.example.dto.questiondto.DeleteQuestionFromQuestionBankDto;
import org.example.model.Course;
import org.example.model.DescriptiveQuestion;
import org.example.model.MultipleChoiceQuestion;
import org.example.repository.base.CourseRepository;
import org.example.service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseRepository.update(course);
    }

    @Override
    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public Course findCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void addStudentToCourse(Long courseId, Long studentId) {
        courseRepository.addStudentToCourse(courseId, studentId);
    }

    @Override
    public void removeStudentFromCourse(Long courseId, Long studentId) {
        courseRepository.removeStudentFromCourse(courseId, studentId);
    }

    @Override
    public void addMasterToCourse(Long courseId, Long masterId) {
        courseRepository.addMasterToCourse(courseId, masterId);
    }

    @Override
    public List<MultipleChoiceQuestion> getMultipleChoiceQuestionsByCourse(Long courseId) {
        return courseRepository.getMultipleChoiceQuestionsByCourse(courseId);
    }

    @Override
    public List<DescriptiveQuestion> getDescriptiveQuestionsByCourse(Long courseId) {
        return courseRepository.getDescriptiveQuestionsByCourse(courseId);
    }

    @Override
    public void deleteQuestionFromCourseBank(Long courseId, Long questionId, String questionType) {
        courseRepository.deleteQuestionFromCourseBank(courseId, questionId, questionType);
    }

    @Override
    public void createCourse(CourseRequestDto courseRequestDto) {

    }

    @Override
    public void updateCourse(Long id, CourseRequestDto courseRequestDto) {

    }

    @Override
    public void updateMasterOfCourse(UpdateMasterOfCourseDto updateMasterOfCourseDto) {

    }

    @Override
    public void deleteCourse(Long id) {

    }

    @Override
    public List<Course> findAll() {
        return List.of();
    }

    @Override
    public void addStudentToCourse(AddStudentToCourseDto addStudentToCourseDto) {

    }

    @Override
    public void deleteStudentFromCourse(DeleteStudentFromCourseDto deleteStudentFromCourseDto) {

    }

    @Override
    public void addMasterToCourse(AddMasterToCourseDto addMasterToCourseDto) {

    }

    @Override
    public Course findById(Long courseId) {
        return null;
    }

    @Override
    public List<MultipleChoiceQuestion> mcqBank(Long courseId) {
        return List.of();
    }

    @Override
    public List<DescriptiveQuestion> dqBank(Long courseId) {
        return List.of();
    }

    @Override
    public void deleteQuestionFromQuestionBank(DeleteQuestionFromQuestionBankDto dto) {

    }
}