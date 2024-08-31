package br.edu.infnet.lessonservice.service.feign;

import br.edu.infnet.lessonservice.service.response.FeedbackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("FEEDBACK-SERVICE")
public interface LessonClient {
    @GetMapping("/feedback/lessons/{lessonId}")
    FeedbackResponse getAllFeedbacksByLessonId(@PathVariable Long lessonId);
}
