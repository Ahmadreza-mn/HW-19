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
@DiscriminatorValue("MULTIPLE_CHOICE_ANSWER")
public class MultipleChoiceAnswer extends Answer {
    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private MultipleChoiceQuestion question;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public void setSelectedOptionIndex(int i) {
    }
}




