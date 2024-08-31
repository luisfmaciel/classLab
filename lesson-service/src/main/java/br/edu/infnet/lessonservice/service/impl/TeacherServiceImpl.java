package br.edu.infnet.lessonservice.service.impl;

import br.edu.infnet.lessonservice.model.Teacher;
import br.edu.infnet.lessonservice.service.TeacherService;
import br.edu.infnet.lessonservice.service.feign.TeacherClient;
import br.edu.infnet.lessonservice.service.response.TeacherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherClient teacherClient;

    @Override
    public Teacher getTeacherById(String id) {
        return teacherClient.getTeacherById(id);
    }
}
