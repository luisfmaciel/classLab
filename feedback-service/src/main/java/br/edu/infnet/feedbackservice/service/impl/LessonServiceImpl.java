package br.edu.infnet.feedbackservice.service.impl;

import br.edu.infnet.feedbackservice.model.Lesson;
import br.edu.infnet.feedbackservice.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    @Override
    public Lesson getLessonById(Long lessonId) {
        RestClient restClient = RestClient.create();
        var serverUrl = String.format("http://localhost:8081/api/lesson/%d", lessonId);
        return restClient.get().uri(serverUrl).retrieve().toEntity(Lesson.class).getBody();
    }
}
