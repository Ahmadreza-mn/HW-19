package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.model.base.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@DiscriminatorColumn(name = "answer_type", discriminatorType = DiscriminatorType.STRING)
public class Answer extends BaseEntity<Long> {

    private double score;

    @ManyToOne
    @JoinColumn(name = "QUESTION_EXAM_ID")
    private QuestionExam questionExam;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID")
    private Student student;
}