package br.edu.infnet.classlab.service;

import br.edu.infnet.classlab.model.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedbacksByLessonId(Long lessonId);
}
