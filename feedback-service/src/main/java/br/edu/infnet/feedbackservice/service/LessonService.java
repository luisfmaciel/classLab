package br.edu.infnet.feedbackservice.service;

import br.edu.infnet.feedbackservice.model.Lesson;

public interface LessonService {
    Lesson getLessonById(String lessonId);
}
