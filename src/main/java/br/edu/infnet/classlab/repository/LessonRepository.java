package br.edu.infnet.classlab.repository;

import br.edu.infnet.classlab.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {}
