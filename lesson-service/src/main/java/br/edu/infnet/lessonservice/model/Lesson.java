package br.edu.infnet.lessonservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "lessons")
public class Lesson {
    @Id
    private String id;
    private String title;
    private String description;
    private String videoId;
    private String lessonType;
    private Date availableAt;
    private String teacherId;
    private int totalSumRating;
    private Classification averageRating;
}
