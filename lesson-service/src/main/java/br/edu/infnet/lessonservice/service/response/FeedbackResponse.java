package br.edu.infnet.lessonservice.service.response;

import br.edu.infnet.lessonservice.model.Feedback;

import java.util.List;

public record FeedbackResponse(List<Feedback> feedbacks) {
}
