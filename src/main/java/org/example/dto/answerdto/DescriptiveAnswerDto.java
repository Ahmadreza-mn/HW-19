package org.example.dto.answerdto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DescriptiveAnswerDto {
    private Long questionId;
    private Long examId;
    private Long studentId;
    private String answer;
    private String newText;
    private String newSampleAnswer;
}