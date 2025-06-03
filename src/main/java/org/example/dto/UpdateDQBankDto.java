package org.example.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDQBankDto {
    private Long questionId;
    @Getter
    private String newText;
    private String newSampleAnswer;
}
