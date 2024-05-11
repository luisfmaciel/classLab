package br.edu.infnet.classlab.controller;

import br.edu.infnet.classlab.model.Lesson;
import br.edu.infnet.classlab.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/api/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @PostMapping
    public Lesson createLesson(@RequestBody Lesson lesson) {
        return lessonService.saveLesson(lesson);
    }

    @GetMapping("/{id}")
    public Optional<Lesson> getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @PutMapping("/{id}")
    public Lesson updateLesson(@PathVariable Long id, @RequestBody Lesson updatedLesson) {
        return lessonService.updateLesson(id, updatedLesson);
    }

    @DeleteMapping("/{id}")
    public void deleteLessonById(@PathVariable Long id) {
        lessonService.deleteLessonById(id);
    }
}
