package br.edu.infnet.feedbackservice.repository;

import br.edu.infnet.feedbackservice.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    Feedback[] findAllByLessonId(Long lessonId);
}
