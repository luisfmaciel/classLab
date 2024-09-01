package br.edu.infnet.lessonservice.service;

import br.edu.infnet.lessonservice.model.Feedback;
import br.edu.infnet.lessonservice.model.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {
    List<Lesson> getAllLessons();
    Lesson saveLesson(Lesson lesson);
    Optional<Lesson> getLessonById(String id);
    List<Lesson> getLessonsByTeacherId(String teacherId);
    List<Lesson> getLessonsByTitle(String title);
    void deleteLessonById(String id) ;
    Lesson updateLesson(String id, Lesson updatedLesson);
    void processAverageRatingLesson(Feedback feedback);
}
