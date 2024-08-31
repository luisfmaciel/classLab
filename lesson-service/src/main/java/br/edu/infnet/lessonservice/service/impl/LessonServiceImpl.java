package br.edu.infnet.lessonservice.service.impl;

import br.edu.infnet.lessonservice.exception.InvalidLessonDataException;
import br.edu.infnet.lessonservice.exception.LessonNotFoundException;
import br.edu.infnet.lessonservice.exception.TeacherNotFoundException;
import br.edu.infnet.lessonservice.model.Lesson;
import br.edu.infnet.lessonservice.repository.LessonRepository;
import br.edu.infnet.lessonservice.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson saveLesson(Lesson lesson) {
        if (lesson == null) {
            throw new InvalidLessonDataException("Dados inválidos fornecidos para a criação da aula");
        }
        return lessonRepository.save(lesson);
    }

    @Override
    public Optional<Lesson> getLessonById(String id) {
        return lessonRepository.findById(id);
    }

    @Override
    public List<Lesson> getLessonsByTeacherId(String teacherId) {
        return lessonRepository.getLessonsByTeacherId(teacherId);
    }

    @Override
    public List<Lesson> getLessonsByTitle(String title) {
        return lessonRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public void deleteLessonById(String id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException("Aula não encontrada para o ID: " + id));
        lessonRepository.delete(lesson);
    }

    @Override
    public Lesson updateLesson(String id, Lesson updatedLesson) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(id);
        if (lessonOptional.isPresent()) {
            updatedLesson.setId(id);
            return lessonRepository.save(updatedLesson);
        }
        return null;
    }
}