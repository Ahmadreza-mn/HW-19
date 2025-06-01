package org.example.service;

import org.example.dto.*;
import org.example.dto.questiondto.DeleteQuestionFromQuestionBankDto;
import org.example.model.Course;
import org.example.model.DescriptiveQuestion;
import org.example.model.MultipleChoiceQuestion;

import java.util.List;

public interface CourseService {
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