package org.example.repository.base;

import org.example.model.Course;
import org.example.model.DescriptiveQuestion;
import org.example.model.MultipleChoiceQuestion;

import java.util.List;

public interface CourseRepository {

    void save(Course course);

    void update(Course course);

    void delete(Course course);

    Course findById(Long courseId);

    List<Course> findAll();

    void addStudentToCourse(Long courseId, Long studentId);

    void removeStudentFromCourse(Long courseId, Long studentId);

    void addMasterToCourse(Long courseId, Long masterId);

    List<MultipleChoiceQuestion> getMultipleChoiceQuestionsByCourse(Long courseId);

    List<DescriptiveQuestion> getDescriptiveQuestionsByCourse(Long courseId);

    void deleteQuestionFromCourseBank(Long courseId, Long questionId, String questionType);
}