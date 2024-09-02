package br.edu.infnet.lessonservice.service;

import br.edu.infnet.lessonservice.model.Teacher;
import br.edu.infnet.lessonservice.service.feign.TeacherClient;
import br.edu.infnet.lessonservice.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TeacherServiceTest {
    @Mock
    TeacherClient teacherClient;
    @InjectMocks
    TeacherServiceImpl teacherService;
    Teacher teacher;

    @BeforeEach
    public void setUp() {
        teacher = Teacher.builder()
                .id("1")
                .name("Test Teacher")
                .avatarUrl("http://example.com/avatar")
                .bio("Test Bio")
                .build();
    }

    @Test
    @DisplayName("Deve retornar um professor ao buscar por ID")
    public void testGetTeacherById() {
        when(teacherClient.getTeacherById("1")).thenReturn(teacher);
        Teacher result = teacherService.getTeacherById("1");
        assertEquals(result.getId(), teacher.getId());
    }
}
