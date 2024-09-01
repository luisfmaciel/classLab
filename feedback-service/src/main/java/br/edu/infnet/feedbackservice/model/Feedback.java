package br.edu.infnet.feedbackservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "feedbacks")
public class Feedback {
    @Id
    private String id;
    private String lessonId;
    private String comment;
    private Classification classification;
    private int totalFeedbacks;

    public static boolean isValidClassification(String value) {
        try {
            Classification.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
