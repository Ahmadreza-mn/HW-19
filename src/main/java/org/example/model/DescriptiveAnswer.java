package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("DESCRIPTIVE_ANSWER")
public class DescriptiveAnswer extends Answer {
    private String answerText;
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;


    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;


    @Getter
    @Setter
    private String text;
    @Getter
    @Setter
    private String sampleAnswer;


}