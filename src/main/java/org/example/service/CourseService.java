package org.example.service;

import org.example.dto.*;
import org.example.dto.questiondto.DeleteQuestionFromQuestionBankDto;
import org.example.model.Course;
import org.example.model.DescriptiveQuestion;
import org.example.model.MultipleChoiceQuestion;

import java.util.List;

public interface CourseService {
    void saveCourse(Course course);

    void updateCourse(Course course);

    void deleteCourse(Course course);

    Course findCourseById(Long courseId);

    List<Course> findAllCourses();

    void addStudentToCourse(Long courseId, Long studentId);

    void removeStudentFromCourse(Long courseId, Long studentId);

    void addMasterToCourse(Long courseId, Long masterId);

    List<MultipleChoiceQuestion> getMultipleChoiceQuestionsByCourse(Long courseId);

    List<DescriptiveQuestion> getDescriptiveQuestionsByCourse(Long courseId);

    void deleteQuestionFromCourseBank(Long courseId, Long questionId, String questionType);

    void createCourse(CourseRequestDto courseRequestDto);

    void updateCourse(Long id, CourseRequestDto courseRequestDto);

    void updateMasterOfCourse(UpdateMasterOfCourseDto updateMasterOfCourseDto);

    void deleteCourse(Long id);

    List<Course> findAll();

    void addStudentToCourse(AddStudentToCourseDto addStudentToCourseDto);

    void deleteStudentFromCourse(DeleteStudentFromCourseDto deleteStudentFromCourseDto);

    void addMasterToCourse(AddMasterToCourseDto addMasterToCourseDto);

    Course findById(Long courseId);

    List<MultipleChoiceQuestion> mcqBank(Long courseId);

    List<DescriptiveQuestion> dqBank(Long courseId);

    void deleteQuestionFromQuestionBank(DeleteQuestionFromQuestionBankDto dto);
}