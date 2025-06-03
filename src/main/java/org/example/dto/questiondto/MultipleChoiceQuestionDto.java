package org.example.dto.questiondto;

import lombok.*;
import org.example.model.Option;
import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap;


import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MultipleChoiceQuestionDto {
    private String title;
    private String questionText;
    private Double defaultScore;
    private List<Option> options;
    private String text;
    private Integer correctOptionIndex;
}
