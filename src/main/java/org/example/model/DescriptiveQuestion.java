package org.example.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("DESCRIPTIVE")
public class DescriptiveQuestion extends Question {
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

}