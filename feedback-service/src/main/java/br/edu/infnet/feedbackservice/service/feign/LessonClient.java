package br.edu.infnet.feedbackservice.service.feign;

import br.edu.infnet.feedbackservice.model.Lesson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("LESSON-SERVICE")
public interface LessonClient {
    @GetMapping("/{lessonId}")
    Lesson getLessonById(@PathVariable String lessonId);
}
