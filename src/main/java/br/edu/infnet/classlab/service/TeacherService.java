package br.edu.infnet.classlab.service;

import br.edu.infnet.classlab.model.Teacher;
import br.edu.infnet.classlab.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
    }

    public  Teacher updateTeacherById(Long id, Teacher teacher) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent()) {
            teacher.setId(id);
            return teacherRepository.save(teacher);
        }
        return null;
    }

    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }
}
