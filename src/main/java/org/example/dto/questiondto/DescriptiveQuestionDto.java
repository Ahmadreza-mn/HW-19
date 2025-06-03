package org.example.dto.questiondto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DescriptiveQuestionDto {
    private String title;
    private String questionText;
    private Double score;
    private String sampleAnswer;
    private String questionId;
    private String text;
}