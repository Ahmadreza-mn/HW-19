package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.model.base.BaseEntity;
import org.example.model.enums.ExamState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Exam extends BaseEntity<Long> {

    private String title;

    private String description;

    @Column(name = "exam_time")
    private Integer examTime;
    @Column(name = "exam_date")

    private LocalDate examDate;

    private Double totalScore;

    @Enumerated(EnumType.STRING)
    private ExamState examState;

    @ManyToOne

    private Course course;

    @ManyToOne
    @JoinColumn(name = "MASTER_ID")
    private Master master;

    @OneToMany(mappedBy = "exam")
    private List<QuestionExam> questionExams = new ArrayList<>();

    @OneToMany(mappedBy = "exam")
    private List<StudentExam> studentExams = new ArrayList<>();
}