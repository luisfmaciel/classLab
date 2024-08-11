package br.edu.infnet.classlab.service;

import br.edu.infnet.classlab.model.Feedback;

import java.util.List;

public record FeedbackResponse(List<Feedback> feedbacks) {
}
