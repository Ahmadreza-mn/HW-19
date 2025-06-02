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
