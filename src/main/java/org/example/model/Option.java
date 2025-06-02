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
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String text;

    @Setter
    private boolean correct;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private MultipleChoiceQuestion question;


    public Option() {}

    public Option(String text, boolean correct, MultipleChoiceQuestion question) {
        this.text = text;
        this.correct = correct;
        this.question = question;
    }


    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public MultipleChoiceQuestion getQuestion() {
        return question;
    }

}