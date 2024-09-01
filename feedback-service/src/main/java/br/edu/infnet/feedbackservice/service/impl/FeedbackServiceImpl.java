package br.edu.infnet.feedbackservice.service.impl;

import br.edu.infnet.feedbackservice.model.Feedback;
import br.edu.infnet.feedbackservice.rabbitmq.FeedbackProducer;
import br.edu.infnet.feedbackservice.repository.FeedbackRepository;
import br.edu.infnet.feedbackservice.service.FeedbackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackProducer feedbackProducer;

    @Override
    public Feedback[] getAllFeedbacksByLessonId(String lessonId) {
        return feedbackRepository.findAllByLessonId(lessonId);
    }

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public void sendFeedback(Feedback feedback) throws JsonProcessingException {
        feedbackProducer.send(feedback);
    }

    @Override
    public int getFeedbackCountByLessonId(String lessonId) {
        return feedbackRepository.countByLessonId(lessonId);
    }

}
