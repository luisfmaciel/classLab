package br.edu.infnet.lessonservice.service.feign;

import br.edu.infnet.lessonservice.model.Teacher;
import br.edu.infnet.lessonservice.service.response.TeacherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("TEACHER-SERVICE")
public interface TeacherClient {
    @GetMapping("/{teacherId}")
    Teacher getTeacherById(@PathVariable String teacherId);
}

