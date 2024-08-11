package br.edu.infnet.classlab.service.impl;

import br.edu.infnet.classlab.exception.InvalidLessonDataException;
import br.edu.infnet.classlab.exception.LessonNotFoundException;
import br.edu.infnet.classlab.exception.TeacherNotFoundException;
import br.edu.infnet.classlab.model.Feedback;
import br.edu.infnet.classlab.model.Lesson;
import br.edu.infnet.classlab.repository.LessonRepository;
import br.edu.infnet.classlab.repository.TeacherRepository;
import br.edu.infnet.classlab.service.FeedbackResponse;
import br.edu.infnet.classlab.service.LessonService;
import br.edu.infnet.classlab.service.feign.LessonClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final LessonClient lessonClient;

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson saveLesson(Lesson lesson, Long teacherId) {
        if (lesson == null || teacherId == null) {
            throw new InvalidLessonDataException("Dados inválidos fornecidos para a criação da aula");
        }

        return teacherRepository.findById(teacherId).map(teacher -> {
            lesson.setTeacher(teacher);
            return lessonRepository.save(lesson);
        }).orElseThrow(() -> new TeacherNotFoundException("Professor não encontrado para o ID: " + teacherId));
    }

    @Override
    public Optional<Lesson> getLessonById(Long id) {
        return lessonRepository.findById(id);
    }

    @Override
    public List<Lesson> getLessonsByTeacherId(Long teacherId) {
        return lessonRepository.getLessonsByTeacherId(teacherId);
    }

    @Override
    public List<Lesson> getLessonsByTitle(String title) {
        return lessonRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public void deleteLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException("Aula não encontrada para o ID: " + id));
        lessonRepository.delete(lesson);
    }

    @Override
    public Lesson updateLesson(Long id, Lesson updatedLesson) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(id);
        if (lessonOptional.isPresent()) {
            updatedLesson.setId(id);
            updatedLesson.setTeacher(lessonOptional.get().getTeacher());
            return lessonRepository.save(updatedLesson);
        }
        return null;
    }
}
