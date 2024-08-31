package br.edu.infnet.teacherservice.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
public class FeedbackServiceTest {
//    @Mock
//    FeedbackServiceImpl feedbackService;
//
//    private Feedback feedback1;
//    private Feedback feedback2;
//    private Long lessonId;
//
//    @BeforeEach
//    public void setUp() {
//        feedback1 = Feedback.builder()
//                .id("1L")
//                .lessonId(1L)
//                .comment("Great lesson!")
//                .classification(Classification.GOOD)
//                .build();
//
//        feedback2 = Feedback.builder()
//                .id("2L")
//                .lessonId(1L)
//                .comment("Needs improvement.")
//                .classification(Classification.AVERAGE)
//                .build();
//
//        lessonId = 1L;
//    }
//
//    @Test
//    @DisplayName("Deve retornar todos os feedbacks para um ID de aula válido.")
//    public void testGetAllFeedbacksByLessonId() {
//        when(feedbackService.getAllFeedbacksByLessonId(lessonId)).thenReturn(Arrays.asList(feedback1, feedback2));
//        List<Feedback> response = feedbackService.getAllFeedbacksByLessonId(lessonId);
//        assertEquals(2, response.size());
//    }
}
