package br.edu.infnet.feedbackservice.service;

import br.edu.infnet.feedbackservice.model.Classification;
import br.edu.infnet.feedbackservice.model.Feedback;
import br.edu.infnet.feedbackservice.rabbitmq.FeedbackProducer;
import br.edu.infnet.feedbackservice.repository.FeedbackRepository;
import br.edu.infnet.feedbackservice.service.impl.FeedbackServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FeedbackServiceTest {
    @Mock
    private FeedbackRepository feedbackRepository;
    @Mock
    private FeedbackProducer feedbackProducer;
    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    private Feedback feedback1;
    private Feedback feedback2;

    @BeforeEach
    public void setUp() {
        feedback1 = Feedback.builder()
                .id("1")
                .lessonId("1")
                .comment("Great lesson!")
                .classification(Classification.GOOD)
                .totalFeedbacks(1)
                .build();

        feedback2 = Feedback.builder()
                .id("2")
                .lessonId("1")
                .comment("Needs improvement.")
                .classification(Classification.AVERAGE)
                .totalFeedbacks(2)
                .build();
    }

    @Test
    @DisplayName("Deve retornar todos os feedbacks para um ID de aula válido.")
    public void testGetAllFeedbacksByLessonId() {
        Feedback[] allFeedbacks = {feedback1, feedback2};
        when(feedbackRepository.findAllByLessonId("1")).thenReturn(allFeedbacks);
        Feedback[] feedbacks = feedbackService.getAllFeedbacksByLessonId("1");
        assertEquals(2, feedbacks.length);
        assertEquals(feedback1.getComment(), feedbacks[0].getComment());
        assertEquals(feedback2.getComment(), feedbacks[1].getComment());
    }

    @Test
    @DisplayName("Deve salvar um feedback com sucesso.")
    public void testSaveFeedback() {
        when(feedbackRepository.save(feedback1)).thenReturn(feedback1);
        Feedback savedFeedback = feedbackService.saveFeedback(feedback1);
        assertEquals(feedback1.getComment(), savedFeedback.getComment());
    }

    @Test
    @DisplayName("Deve retornar a quantidade de aulas pelo ID.")
    public void testGetFeedbackCountByLessonId() {
        String lessonId = "1";
        int expectedCount = 5;
        when(feedbackRepository.countByLessonId(lessonId)).thenReturn(expectedCount);
        int actualCount = feedbackService.getFeedbackCountByLessonId(lessonId);
        verify(feedbackRepository).countByLessonId(lessonId);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("Deve enviar feedback com sucesso")
    public void testSendFeedback() throws JsonProcessingException {
        doNothing().when(feedbackProducer).send(any(Feedback.class));
        feedbackService.sendFeedback(feedback1);
        verify(feedbackProducer, times(1)).send(feedback1);
    }
}
