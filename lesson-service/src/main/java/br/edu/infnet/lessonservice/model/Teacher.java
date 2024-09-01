package br.edu.infnet.lessonservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Teacher {
    @Id
    private String id;
    private String name;
    private String avatarUrl;
    private String bio;
}
