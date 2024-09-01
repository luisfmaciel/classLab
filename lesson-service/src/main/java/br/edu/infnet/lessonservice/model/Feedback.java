package br.edu.infnet.lessonservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Feedback {
    @Id
    private String id;
    private String lessonId;
    private String comment;
    private Classification classification;
    private int totalFeedbacks;
}
