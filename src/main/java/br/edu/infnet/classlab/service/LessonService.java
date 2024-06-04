package br.edu.infnet.classlab.service;

import br.edu.infnet.classlab.exception.InvalidLessonDataException;
import br.edu.infnet.classlab.exception.LessonNotFoundException;
import br.edu.infnet.classlab.exception.TeacherNotFoundException;
import br.edu.infnet.classlab.model.Lesson;
import br.edu.infnet.classlab.repository.LessonRepository;
import br.edu.infnet.classlab.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson saveLesson(Lesson lesson, Long teacherId) {
        if (lesson == null || teacherId == null) {
            throw new InvalidLessonDataException("Dados inválidos fornecidos para a criação da aula");
        }

        return teacherRepository.findById(teacherId).map(teacher -> {
            lesson.setTeacher(teacher);
            return lessonRepository.save(lesson);
        }).orElseThrow(() -> new TeacherNotFoundException("Professor não encontrado para o ID: " + teacherId));
    }

    public Optional<Lesson> getLessonById(Long id) {
        return lessonRepository.findById(id);
    }

    public void deleteLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException("Aula não encontrada para o ID: " + id));
        lessonRepository.delete(lesson);
    }

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
