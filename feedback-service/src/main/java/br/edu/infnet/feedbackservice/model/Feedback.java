package br.edu.infnet.feedbackservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "feedbacks")
public class Feedback {
    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("lessonId")
    private Long lessonId;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("classification")
    private Classification classification;

    public static boolean isValidClassification(String value) {
        try {
            Classification.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
