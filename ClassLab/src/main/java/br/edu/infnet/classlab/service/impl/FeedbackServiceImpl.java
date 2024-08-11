package br.edu.infnet.classlab.service.impl;

import br.edu.infnet.classlab.model.Feedback;
import br.edu.infnet.classlab.service.FeedbackResponse;
import br.edu.infnet.classlab.service.FeedbackService;
import br.edu.infnet.classlab.service.feign.LessonClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final LessonClient lessonClient;

    @Override
    public List<Feedback> getAllFeedbacksByLessonId(Long lessonId) {
        FeedbackResponse response = lessonClient.getAllFeedbacksByLessonId(lessonId);
        if (response != null) {
            return response.feedbacks();
        }
        return null;
    }
}
