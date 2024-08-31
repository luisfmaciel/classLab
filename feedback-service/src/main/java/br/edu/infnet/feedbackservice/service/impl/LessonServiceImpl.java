package br.edu.infnet.feedbackservice.service.impl;

import br.edu.infnet.feedbackservice.model.Lesson;
import br.edu.infnet.feedbackservice.service.LessonService;
import br.edu.infnet.feedbackservice.service.feign.LessonClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonClient lessonClient;
    @Override
    public Lesson getLessonById(String lessonId) {
        return lessonClient.getLessonById(lessonId);
    }
}
