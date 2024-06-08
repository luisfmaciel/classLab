package br.edu.infnet.classlab.repository;

import br.edu.infnet.classlab.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> getLessonsByTeacherId(Long teacherId);
    List<Lesson> findByTitleContainingIgnoreCase(String title);
}
