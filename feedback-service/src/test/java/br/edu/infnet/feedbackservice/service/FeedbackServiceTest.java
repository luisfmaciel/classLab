package br.edu.infnet.feedbackservice.service;

import br.edu.infnet.feedbackservice.model.Classification;
import br.edu.infnet.feedbackservice.model.Feedback;
import br.edu.infnet.feedbackservice.repository.FeedbackRepository;
import br.edu.infnet.feedbackservice.service.impl.FeedbackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FeedbackServiceTest {
    @Mock
    private FeedbackRepository feedbackRepository;
    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    private Feedback feedback1;
    private Feedback feedback2;

    @BeforeEach
    public void setUp() {
        feedback1 = Feedback.builder()
                .id("1L")
                .lessonId(1L)
                .comment("Great lesson!")
                .classification(Classification.GOOD)
                .build();

        feedback2 = Feedback.builder()
                .id("2L")
                .lessonId(1L)
                .comment("Needs improvement.")
                .classification(Classification.AVERAGE)
                .build();
    }

    @Test
    @DisplayName("Deve retornar todos os feedbacks para um ID de aula válido.")
    public void testGetAllFeedbacksByLessonId() {
        when(feedbackRepository.findAllByLessonId(1L)).thenReturn(Arrays.asList(feedback1, feedback2));
        List<Feedback> feedbacks = feedbackService.getAllFeedbacksByLessonId(1L);
        assertEquals(2, feedbacks.size());
        assertEquals(feedback1.getComment(), feedbacks.get(0).getComment());
        assertEquals(feedback2.getComment(), feedbacks.get(1).getComment());
    }

    @Test
    @DisplayName("Deve salvar um feedback com sucesso.")
    public void testSaveFeedback() {
        when(feedbackRepository.save(feedback1)).thenReturn(feedback1);
        Feedback savedFeedback = feedbackService.saveFeedback(feedback1);
        assertEquals(feedback1.getComment(), savedFeedback.getComment());
    }
}
