package br.edu.infnet.feedbackservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {
    @Id
    private String id;
    private String title;
    private String description;
    private String videoId;
    private String lessonType;
    private Date availableAt;
    private String teacherId;
}
