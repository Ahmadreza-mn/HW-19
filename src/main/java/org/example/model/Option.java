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
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = "options")
public class Option extends BaseEntity<Long> {
    @Column(nullable = false)
    private String optionText;

    @Column(nullable = false)
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private MultipleChoiceQuestion question;

    @OneToMany(mappedBy = "option")
    private List<MultipleChoiceAnswer> answers = new ArrayList<>();

}