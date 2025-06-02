package org.example.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentExamDto {
    private Long studentId;
    private Long examId;
    private LocalDateTime startTime;
}