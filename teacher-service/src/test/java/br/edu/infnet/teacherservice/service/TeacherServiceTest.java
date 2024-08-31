package br.edu.infnet.teacherservice.service;

import br.edu.infnet.teacherservice.model.Teacher;
import br.edu.infnet.teacherservice.service.impl.TeacherServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class TeacherServiceTest {
//
//    @Autowired
//    LessonServiceImpl lessonService;
    @Autowired
    TeacherServiceImpl teacherService;

//    private Lesson lesson;
    private Teacher teacher;

    @BeforeEach
    public void setUp() {
        teacher = Teacher.builder()
                .id("1L")
                .name("Test Teacher 1")
                .avatarUrl("http://example.com/avatar")
                .bio("Test Bio")
                .build();

//        lesson = new Lesson();
//        lesson.setId(1L);
//        lesson.setTitle("Test Lesson 1");
//        lesson.setDescription("Description for Test Lesson 1");
//        lesson.setVideoId("12345");
//        lesson.setLessonType("Video");
//        lesson.setAvailableAt(new Date());
//        lesson.setTeacher(teacher);
    }

    @Test
    @DisplayName("Deve retornar todos os professores.")
    public void testGetAllTeachers() {
        List<Teacher> allTeacher = teacherService.getAllTeachers();
        assertEquals(2, allTeacher.size());
    }

    @Test
    @DisplayName("Deve salvar um professor com sucesso.")
    public void testSaveTeacher() {
        List<Teacher> allTeacher = teacherService.getAllTeachers();
        int initialSize = allTeacher.size();
        teacher.setId("3L");
        teacherService.saveTeacher(teacher);
        allTeacher = teacherService.getAllTeachers();
        int finalSize = allTeacher.size();
        assertEquals(initialSize + 1, finalSize);
    }

    @Test
    @DisplayName("Deve retornar um professor pelo ID.")
    public void testGetTeacherById() {
        Optional<Teacher> foundTeacher = teacherService.getTeacherById(teacher.getId());
        assertTrue(foundTeacher.isPresent());
        assertEquals(teacher.getName(), foundTeacher.get().getName());
    }

    @Test
    @DisplayName("Deve retornar um professor pelo nome.")
    public void testGetTeachersByName() {
        List<Teacher> teachersByName = teacherService.getTeachersByName(teacher.getName());
        assertEquals(1, teachersByName.size());
        assertEquals(teacher.getName(), teachersByName.get(0).getName());
    }

    @Test
    @DisplayName("Deve retornar se um professor existe pelo ID.")
    public void testExistsTeacherById() {
        boolean exists = teacherService.existsTeacherById(teacher.getId());
        assertTrue(exists);
    }

    @Test
    @DisplayName("Deve atualizar um professor com sucesso.")
    public void testUpdateTeacherById() {
        Teacher updatedTeacher = teacherService.getTeacherById(teacher.getId()).get();
        updatedTeacher.setName("Updated Teacher");
        Teacher result = teacherService.updateTeacherById(teacher.getId(), updatedTeacher);
        assertEquals("Updated Teacher", result.getName());
    }

    @Test
    @DisplayName("Deve remover um professor com sucesso.")
    public void testDeleteTeacherById() {
        List<Teacher> allTeachers = teacherService.getAllTeachers();
        int initialSize = allTeachers.size();
        teacherService.deleteTeacherById(teacher.getId());
        allTeachers = teacherService.getAllTeachers();
        int finalSize = allTeachers.size();
        assertEquals(initialSize - 1, finalSize);
    }
}
