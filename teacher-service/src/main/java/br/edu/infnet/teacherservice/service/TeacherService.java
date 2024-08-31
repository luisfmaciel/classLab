package br.edu.infnet.teacherservice.service;

import br.edu.infnet.teacherservice.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Optional<Teacher> getTeacherById(String id);
    List<Teacher> getTeachersByName(String name);
    Teacher saveTeacher(Teacher teacher) ;
    boolean existsTeacherById(String id);
    Teacher updateTeacherById(String id, Teacher teacher);
    void deleteTeacherById(String id);
}
