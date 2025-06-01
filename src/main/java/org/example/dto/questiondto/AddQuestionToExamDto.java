package org.example.dto.questiondto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddQuestionToExamDto {
    private Long examId;
    private Long questionId;
    private Long questionExamId;
    private Double score;
}