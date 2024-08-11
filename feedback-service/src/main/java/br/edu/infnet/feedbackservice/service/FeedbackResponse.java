package br.edu.infnet.feedbackservice.service;

import br.edu.infnet.feedbackservice.model.Feedback;

import java.util.List;

public record FeedbackResponse(List<Feedback> feedbacks) {
}
