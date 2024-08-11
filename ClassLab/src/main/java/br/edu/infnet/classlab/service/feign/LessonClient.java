package br.edu.infnet.classlab.service.feign;

import br.edu.infnet.classlab.service.FeedbackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("FEEDBACK-SERVICE")
public interface LessonClient {
    @GetMapping("/api/feedback/lessons/{lessonId}")
    FeedbackResponse getAllFeedbacksByLessonId(@PathVariable Long lessonId);
}
