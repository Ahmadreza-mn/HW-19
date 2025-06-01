package org.example.dto.questiondto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionDto {
    private Long id;
    private String optionText;
    private boolean isCorrect;

}