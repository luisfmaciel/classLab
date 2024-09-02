package br.edu.infnet.teacherservice.service;

import br.edu.infnet.teacherservice.model.Teacher;
import br.edu.infnet.teacherservice.repository.TeacherRepository;
import br.edu.infnet.teacherservice.service.impl.TeacherServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class TeacherServiceTest {
    @InjectMocks
    TeacherServiceImpl teacherService;
    @Mock
    TeacherRepository teacherRepository;

    private Teacher teacher;

    @BeforeEach
    public void setUp() {
        teacher = Teacher.builder()
                .id("1")
                .name("Test Teacher 1")
                .avatarUrl("http://example.com/avatar")
                .bio("Test Bio")
                .build();

    }

    @Test
    @DisplayName("Deve retornar todos os professores.")
    public void testGetAllTeachers() {
        when(teacherRepository.findAll()).thenReturn(Collections.singletonList(teacher));
        List<Teacher> allTeacher = teacherService.getAllTeachers();
        assertEquals(1, allTeacher.size());
    }

    @Test
    @DisplayName("Deve salvar um professor com sucesso.")
    public void testSaveTeacher() {
        List<Teacher> teachers = new ArrayList<>();
        when(teacherRepository.findAll()).thenReturn(teachers);
        List<Teacher> allTeacher = teacherService.getAllTeachers();
        int initialSize = allTeacher.size();
        when(teacherRepository.save(teacher)).thenAnswer(invocation -> {
            teachers.add(teacher);
            return teacher;
        });
        teacherService.saveTeacher(teacher);
        allTeacher = teacherService.getAllTeachers();
        int finalSize = allTeacher.size();
        assertEquals(initialSize + 1, finalSize);
    }

    @Test
    @DisplayName("Deve retornar um professor pelo ID.")
    public void testGetTeacherById() {
        when(teacherRepository.findById(teacher.getId())).thenReturn(Optional.ofNullable(teacher));
        Optional<Teacher> foundTeacher = teacherService.getTeacherById(teacher.getId());
        assertTrue(foundTeacher.isPresent());
        assertEquals(teacher.getName(), foundTeacher.get().getName());
    }

    @Test
    @DisplayName("Deve retornar um professor pelo nome.")
    public void testGetTeachersByName() {
        when(teacherRepository.findByNameContainingIgnoreCase(teacher.getName())).thenReturn(Collections.singletonList(teacher));
        List<Teacher> teachersByName = teacherService.getTeachersByName(teacher.getName());
        assertEquals(1, teachersByName.size());
        assertEquals(teacher.getName(), teachersByName.get(0).getName());
    }

    @Test
    @DisplayName("Deve retornar se um professor existe pelo ID.")
    public void testExistsTeacherById() {
        when(teacherRepository.existsById(teacher.getId())).thenReturn(true);
        boolean exists = teacherService.existsTeacherById(teacher.getId());
        assertTrue(exists);
    }

    @Test
    @DisplayName("Deve atualizar um professor com sucesso.")
    public void testUpdateTeacherById() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        when(teacherRepository.findById(teacher.getId())).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(any(Teacher.class))).thenAnswer(invocation -> {
            Teacher updatedTeacher = invocation.getArgument(0);
            teachers.set(0, updatedTeacher);
            return updatedTeacher;
        });
        Teacher updatedTeacher = teacherService.getTeacherById(teacher.getId()).get();
        updatedTeacher.setName("Updated Teacher");
        Teacher result = teacherService.updateTeacherById(teacher.getId(), updatedTeacher);
        assertEquals("Updated Teacher", result.getName());
    }

    @Test
    @DisplayName("Deve remover um professor com sucesso.")
    public void testDeleteTeacherById() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        when(teacherRepository.findAll()).thenReturn(teachers);
        when(teacherRepository.findById(teacher.getId())).thenReturn(Optional.of(teacher));

        List<Teacher> allTeachers = teacherService.getAllTeachers();
        int initialSize = allTeachers.size();

        doAnswer(invocationOnMock -> {
            Teacher teacher = invocationOnMock.getArgument(0);
            teachers.remove(teacher);
            return null;
        }).when(teacherRepository).delete(teacher);

        teacherService.deleteTeacherById(teacher.getId());
        when(teacherRepository.findAll()).thenReturn(teachers);
        allTeachers = teacherService.getAllTeachers();
        int finalSize = allTeachers.size();

        assertEquals(initialSize - 1, finalSize);
    }
}
