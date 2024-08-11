package br.edu.infnet.feedbackservice.service.impl;

import br.edu.infnet.feedbackservice.model.Feedback;
import br.edu.infnet.feedbackservice.repository.FeedbackRepository;
import br.edu.infnet.feedbackservice.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Override
    public Feedback[] getAllFeedbacksByLessonId(Long lessonId) {
        return feedbackRepository.findAllByLessonId(lessonId);
    }

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

}
