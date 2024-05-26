package br.edu.infnet.classlab.service;

import br.edu.infnet.classlab.model.Lesson;
import br.edu.infnet.classlab.model.Teacher;
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
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (teacher.isPresent()) {
            lesson.setTeacher(teacher.get());
            return lessonRepository.save(lesson);
        }
        return null;
    }

    public Optional<Lesson> getLessonById(Long id) {
        return lessonRepository.findById(id);
    }

    public void deleteLessonById(Long id) {
        lessonRepository.deleteById(id);
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
