package org.example.dto.questiondto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteQuestionFromExamDto {
    private Long ExamId;
    private Long questionId;
}