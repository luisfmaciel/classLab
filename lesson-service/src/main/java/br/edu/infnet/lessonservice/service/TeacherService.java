package br.edu.infnet.lessonservice.service;

import br.edu.infnet.lessonservice.model.Teacher;
import br.edu.infnet.lessonservice.service.response.TeacherResponse;

public interface TeacherService {
    Teacher getTeacherById(String id);
}
