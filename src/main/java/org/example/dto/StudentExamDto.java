package org.example.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentExamDto {
    private Long studentId;
    private Long examId;
}