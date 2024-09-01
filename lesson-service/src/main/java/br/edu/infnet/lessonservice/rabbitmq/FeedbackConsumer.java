package br.edu.infnet.lessonservice.rabbitmq;

import br.edu.infnet.lessonservice.model.Classification;
import br.edu.infnet.lessonservice.model.Feedback;
import br.edu.infnet.lessonservice.model.Lesson;
import br.edu.infnet.lessonservice.service.LessonService;
import br.edu.infnet.lessonservice.service.impl.LessonServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FeedbackConsumer {

    private final LessonServiceImpl lessonService;

    @RabbitListener(queues = {"feedback-queue"})
    public void receiveFeedback(@Payload String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Feedback feedback = mapper.readValue(message, Feedback.class);

        lessonService.processAverageRatingLesson(feedback);
    }
}
