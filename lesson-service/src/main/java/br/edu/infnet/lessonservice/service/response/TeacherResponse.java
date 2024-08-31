package br.edu.infnet.lessonservice.service.response;

import br.edu.infnet.lessonservice.model.Teacher;
import org.springframework.http.ResponseEntity;

public record TeacherResponse(ResponseEntity<Teacher> teacher) {
}
