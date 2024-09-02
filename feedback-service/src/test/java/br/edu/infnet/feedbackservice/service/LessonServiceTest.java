package br.edu.infnet.feedbackservice.service;


import br.edu.infnet.feedbackservice.model.Classification;
import br.edu.infnet.feedbackservice.model.Feedback;
import br.edu.infnet.feedbackservice.model.Lesson;
import br.edu.infnet.feedbackservice.rabbitmq.FeedbackProducer;
import br.edu.infnet.feedbackservice.repository.FeedbackRepository;
import br.edu.infnet.feedbackservice.service.feign.LessonClient;
import br.edu.infnet.feedbackservice.service.impl.FeedbackServiceImpl;
import br.edu.infnet.feedbackservice.service.impl.LessonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;


@SpringBootTest
public class LessonServiceTest {
    @Mock
    private LessonClient lessonClient;

    @InjectMocks
    private LessonServiceImpl lessonService;

    private Lesson lesson;

    @BeforeEach
    public void setUp() {
        lesson = Lesson.builder()
                .id("1")
                .title("Introduction to Java")
                .description("Basic Java course")
                .build();
    }

    @Test
    @DisplayName("Deve retornar a lição por ID com sucesso")
    public void testGetLessonById() {
        when(lessonClient.getLessonById(anyString())).thenReturn(lesson);
        Lesson result = lessonService.getLessonById("1");
        Assertions.assertEquals(lesson, result, "A lição retornada pelo serviço deve ser igual ao mock.");
        verify(lessonClient).getLessonById("1");
    }
}
