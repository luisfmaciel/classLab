package br.edu.infnet.lessonservice.repository;

import br.edu.infnet.lessonservice.model.Lesson;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LessonRepository extends MongoRepository<Lesson, String> {
    List<Lesson> getLessonsByTeacherId(String teacherId);
    List<Lesson> findByTitleContainingIgnoreCase(String title);
}
