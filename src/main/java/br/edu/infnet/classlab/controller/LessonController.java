package br.edu.infnet.classlab.controller;

import br.edu.infnet.classlab.model.Lesson;
import br.edu.infnet.classlab.model.Teacher;
import br.edu.infnet.classlab.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonService.getAllLessons();
        return lessons.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lessons);
    }

    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        Lesson newLesson = lessonService.saveLesson(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLesson);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson updatedLesson) {
        Lesson lesson = lessonService.updateLesson(id, updatedLesson);
        return ResponseEntity.ok(lesson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Lesson> deleteLessonById(@PathVariable Long id) {
        lessonService.deleteLessonById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assign-teacher")
    public ResponseEntity<Lesson> assignTeacherToLesson(@RequestParam Long lessonId, @RequestParam Long teacherId) {
        Lesson assignedLesson = lessonService.assignTeacherToLesson(lessonId, teacherId);
        return ResponseEntity.ok(assignedLesson);
    }
}
