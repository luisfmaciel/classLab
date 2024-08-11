package br.edu.infnet.feedbackservice.service.feign;

import br.edu.infnet.feedbackservice.model.Lesson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CLASSLAB")
public interface LessonClient {
    @GetMapping("/api/lesson/{lessonId}")
    Lesson getLessonById(@PathVariable Long lessonId);
}
