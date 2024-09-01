package br.edu.infnet.feedbackservice.rabbitmq;

import br.edu.infnet.feedbackservice.model.Feedback;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackProducer {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;
    public void send(Feedback feedback) throws JsonProcessingException {
        amqpTemplate.convertAndSend("feedback-exc", "feedback-rk", objectMapper.writeValueAsString(feedback));
    }

}
