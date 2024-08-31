package br.edu.infnet.lessonservice.service;

import br.edu.infnet.lessonservice.model.Lesson;
import br.edu.infnet.lessonservice.service.impl.LessonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class LessonServiceTest {

    @Autowired
    LessonServiceImpl lessonService;

    private Lesson lesson;

    @BeforeEach
    public void setUp() {
        lesson = new Lesson();
        lesson.setId("1L");
        lesson.setTitle("Test Lesson 1");
        lesson.setDescription("Description for Test Lesson 1");
        lesson.setVideoId("12345");
        lesson.setLessonType("Video");
        lesson.setAvailableAt(new Date());
        lesson.setTeacherId("1L");
//        lesson.setTeacher(teacher);
    }

    @Test
    @DisplayName("Deve retornar todas as aulas.")
    public void testGetAllLessons() {
        List<Lesson> allLessons = lessonService.getAllLessons();
        assertEquals(2, allLessons.size());
    }
//
//    @Test
//    @DisplayName("Deve salvar uma aula com sucesso.")
//    public void testSaveLesson() {
//        Optional<Teacher> optionalTeacher = teacherService.getTeachersByName(teacher.getName()).stream().findFirst();
//        List<Lesson> allLessons = lessonService.getAllLessons();
//        int initialSize = allLessons.size();
//        lesson.setId(3L);
//        lessonService.saveLesson(lesson, optionalTeacher.get().getId());
//        allLessons = lessonService.getAllLessons();
//        int finalSize = allLessons.size();
//        assertEquals(initialSize + 1, finalSize);
//    }
//
//    @Test
//    @DisplayName("Deve retornar as aulas pelo ID do professor.")
//    public void testGetLessonsByTeacherId() {
//        Optional<Teacher> optionalTeacher = teacherService.getTeacherById(teacher.getId());
//        List<Lesson> lessonsByTeacher = lessonService.getLessonsByTeacherId(optionalTeacher.get().getId());
//        assertEquals(1, lessonsByTeacher.size());
//    }

    @Test
    @DisplayName("Deve retornar as aulas pelo título.")
    public void testGetLessonsByTitle() {
        List<Lesson> lessons = lessonService.getLessonsByTitle(lesson.getTitle());
        assertEquals(lesson.getTitle(), lessons.get(0).getTitle());
    }

    @Test
    @DisplayName("Deve atualizar uma aula com sucsso.")
    public void testUpdateLesson() {
        Optional<Lesson> updatedLesson = lessonService.getLessonById(lesson.getId());

        updatedLesson.get().setTitle("Updated Lesson");
        Lesson result = lessonService.updateLesson(lesson.getId(), updatedLesson.get());
        assertEquals("Updated Lesson", result.getTitle());
    }

    @Test
    @DisplayName("Deve remover uma aula com sucesso.")
    public void testDeleteLessonById() {
        List<Lesson> allLessons = lessonService.getAllLessons();
        int initialSize = allLessons.size();
        lessonService.deleteLessonById(lesson.getId());
        allLessons = lessonService.getAllLessons();
        int finalSize = allLessons.size();
        assertEquals(initialSize - 1, finalSize);
    }


}
