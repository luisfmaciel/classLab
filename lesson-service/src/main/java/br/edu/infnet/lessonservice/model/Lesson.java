package br.edu.infnet.lessonservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "lessons")
public class Lesson {
    @Id
    private String id;
    private String title;
    private String description;
    private String videoId;
    private String lessonType;
    private String teacherId;
}
