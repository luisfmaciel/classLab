package br.edu.infnet.classlab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feedback {
    @Id
    private String id;
    private Long lessonId;
    private String comment;
    private Classification classification;
}
