package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.model.base.BaseEntity;
import org.example.model.enums.StudentExamStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StudentExam extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;
    @Enumerated(EnumType.STRING)
    private StudentExamStatus studentExamStatus;
    private LocalDateTime startedAt;
    private LocalDateTime endAt;
    private Double examScoreForStudent;
}