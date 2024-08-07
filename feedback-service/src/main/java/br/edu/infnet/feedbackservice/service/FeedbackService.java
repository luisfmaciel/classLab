package br.edu.infnet.feedbackservice.service;

import br.edu.infnet.feedbackservice.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback saveFeedback(Feedback feedback);
    List<Feedback> getAllFeedbacksByLessonId(Long lessonId);
}
