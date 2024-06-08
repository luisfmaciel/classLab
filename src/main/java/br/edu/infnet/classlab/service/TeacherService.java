package br.edu.infnet.classlab.service;

import br.edu.infnet.classlab.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Optional<Teacher> getTeacherById(Long id);
    Teacher saveTeacher(Teacher teacher) ;
    Teacher updateTeacherById(Long id, Teacher teacher);
    void deleteTeacherById(Long id);
}
