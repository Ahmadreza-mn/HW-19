package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.model.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question_exam")
public class QuestionExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;


    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;


    @Setter
    private Integer questionOrder;


    public QuestionExam() {}

    public QuestionExam(Exam exam, Question question, Integer questionOrder) {
        this.exam = exam;
        this.question = question;
        this.questionOrder = questionOrder;
    }


    public Long getId() {
        return id;
    }

    public Exam getExam() {
        return exam;
    }

    public Question getQuestion() {
        return question;
    }

    public Integer getQuestionOrder() {
        return questionOrder;
    }

}