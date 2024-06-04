package br.edu.infnet.classlab.service;

import br.edu.infnet.classlab.exception.InvalidTeacherDataException;
import br.edu.infnet.classlab.exception.TeacherNotFoundException;
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
        if (teacher == null) {
            throw new InvalidTeacherDataException("Dados inválidos fornecidos para a criação do professor");
        }
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacherById(Long id, Teacher teacher) {
        if (teacher == null) {
            throw new InvalidTeacherDataException("Dados inválidos fornecidos para a atualização do professor");
        }
        return teacherRepository.findById(id)
                .map(existingTeacher -> {
                    teacher.setId(existingTeacher.getId());
                    return teacherRepository.save(teacher);
                })
                .orElseThrow(() -> new TeacherNotFoundException("Professor não encontrado para o ID: " + id));
    }

    public void deleteTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Professor não encontrado para o ID: " + id));
        teacherRepository.delete(teacher);
    }
}
