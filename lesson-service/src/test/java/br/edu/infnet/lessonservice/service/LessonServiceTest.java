package br.edu.infnet.lessonservice.service;

import br.edu.infnet.lessonservice.exception.LessonNotFoundException;
import br.edu.infnet.lessonservice.model.Classification;
import br.edu.infnet.lessonservice.model.Feedback;
import br.edu.infnet.lessonservice.model.Lesson;
import br.edu.infnet.lessonservice.repository.LessonRepository;
import br.edu.infnet.lessonservice.service.impl.LessonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class LessonServiceTest {

    @Mock
    LessonRepository lessonRepository;
    @InjectMocks
    LessonServiceImpl lessonService;

    private Lesson lesson;
    private Feedback feedback;

    @BeforeEach
    public void setUp() {
        lesson = new Lesson();
        lesson.setId("1");
        lesson.setTitle("Test Lesson 1");
        lesson.setDescription("Description for Test Lesson 1");
        lesson.setVideoId("12345");
        lesson.setLessonType("Video");
        lesson.setAvailableAt(new Date());
        lesson.setTeacherId("1");
        lesson.setAverageRating(Classification.valueOf("GOOD"));
        lesson.setTotalSumRating(20);

        feedback = Feedback.builder()
                .lessonId(lesson.getId())
                .classification(Classification.EXCELLENT)
                .totalFeedbacks(5)
                .build();
    }

    @Test
    @DisplayName("Deve retornar todas as aulas.")
    public void testGetAllLessons() {
        when(lessonRepository.findAll()).thenReturn(Collections.singletonList(lesson));
        List<Lesson> allLessons = lessonService.getAllLessons();
        assertEquals(1, allLessons.size());
    }

    @Test
    @DisplayName("Deve salvar uma aula com sucesso.")
    public void testSaveLesson() {
        List<Lesson> lessons = new ArrayList<>();
        when(lessonRepository.findAll()).thenReturn(lessons);
        List<Lesson> allLessons = lessonService.getAllLessons();
        int initialSize = allLessons.size();
        when(lessonRepository.save(lesson)).thenAnswer(invocation -> {
            lessons.add(lesson);
            return lesson;
        });
        lessonService.saveLesson(lesson);
        allLessons = lessonService.getAllLessons();
        int finalSize = allLessons.size();
        assertEquals(initialSize + 1, finalSize);
    }

    @Test
    @DisplayName("Deve retornar as aulas pelo ID do professor.")
    public void testGetLessonsByTeacherId() {
        when(lessonRepository.getLessonsByTeacherId(lesson.getTeacherId())).thenReturn(Collections.singletonList(lesson));
        List<Lesson> lessonsByTeacher = lessonService.getLessonsByTeacherId(lesson.getTeacherId());
        assertEquals(1, lessonsByTeacher.size());
    }

    @Test
    @DisplayName("Deve retornar as aulas pelo título.")
    public void testGetLessonsByTitle() {
        when(lessonRepository.findByTitleContainingIgnoreCase(lesson.getTitle())).thenReturn(Collections.singletonList(lesson));
        List<Lesson> lessons = lessonService.getLessonsByTitle(lesson.getTitle());
        assertEquals(lesson.getTitle(), lessons.get(0).getTitle());
    }

    @Test
    @DisplayName("Deve atualizar uma aula com sucsso.")
    public void testUpdateLesson() {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);
        when(lessonRepository.findById(lesson.getId())).thenReturn(Optional.of(lesson));
        when(lessonRepository.save(any(Lesson.class))).thenAnswer(invocation -> {
            Lesson lesson1 = invocation.getArgument(0);
            lessons.set(0, lesson1);
            return lesson1;
        });
        Lesson lesson1 = lessonService.getLessonById(lesson.getId()).get();
        lesson1.setTitle("Updated Teste Lesson Title");
        Lesson result = lessonService.updateLesson(lesson.getId(), lesson1);
        assertEquals("Updated Teste Lesson Title", result.getTitle());
    }

    @Test
    @DisplayName("Deve remover uma aula com sucesso.")
    public void testDeleteLessonById() {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);
        when(lessonRepository.findAll()).thenReturn(lessons);
        when(lessonRepository.findById(lesson.getId())).thenReturn(Optional.ofNullable(lesson));

        List<Lesson> allLessons = lessonService.getAllLessons();
        int initialSize = allLessons.size();

        doAnswer(invocation -> {
            Lesson lesson1 = invocation.getArgument(0);
            lessons.remove(lesson1);
            return null;
        }).when(lessonRepository).delete(lesson);

        lessonService.deleteLessonById(lesson.getId());
        when(lessonService.getAllLessons()).thenReturn(List.of());
        allLessons = lessonService.getAllLessons();
        int finalSize = allLessons.size();
        assertEquals(initialSize - 1, finalSize);
    }


    @Test
    @DisplayName("Deve retornar a classificação média da aula.")
    public void testProcessAverageRatingLesson() {
        when(lessonRepository.findById(lesson.getId())).thenReturn(Optional.of(lesson));

        int classificationValue = 5;

        int totalSumRating = lesson.getTotalSumRating();
        totalSumRating += classificationValue;
        Classification expectedAverageClassification = Classification.calculateAverageClassification(totalSumRating, feedback.getTotalFeedbacks(), feedback.getClassification());

        lessonService.processAverageRatingLesson(feedback);

        assertEquals(totalSumRating, lesson.getTotalSumRating());
        assertEquals(expectedAverageClassification, lesson.getAverageRating());

    }
}
