package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.model.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "question")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type", discriminatorType = DiscriminatorType.STRING)
public class Question extends BaseEntity<Long> {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, name = "qustion_text")
    private String questionText;
    @Column(nullable = false)
    private Double score;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionExam> questionExams = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    private Course course;
    @Column(nullable = false)
    private boolean deleted = false;

}