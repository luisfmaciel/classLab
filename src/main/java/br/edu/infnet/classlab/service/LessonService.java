package br.edu.infnet.classlab.service;

import br.edu.infnet.classlab.model.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {
    List<Lesson> getAllLessons();
    Lesson saveLesson(Lesson lesson, Long teacherId);
    Optional<Lesson> getLessonById(Long id);
    List<Lesson> getLessonsByTeacherId(Long teacherId);
    List<Lesson> getLessonsByTitle(String title);
    void deleteLessonById(Long id) ;
    Lesson updateLesson(Long id, Lesson updatedLesson);
}
