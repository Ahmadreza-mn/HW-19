package org.example.dto.questiondto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDQBankDto {
    private Long questionId;
    private String title;
    private String questionText;
    private Double defaultScore;
    @Setter
    public String NewText;
    public String NewSampleAnswer;

}