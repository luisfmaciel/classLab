package br.edu.infnet.classlab.service;

import br.edu.infnet.classlab.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Optional<Teacher> getTeacherById(Long id);
    List<Teacher> getTeachersByName(String name);
    Teacher saveTeacher(Teacher teacher) ;
    boolean existsTeacherById(Long id);
    Teacher updateTeacherById(Long id, Teacher teacher);
    void deleteTeacherById(Long id);
}
