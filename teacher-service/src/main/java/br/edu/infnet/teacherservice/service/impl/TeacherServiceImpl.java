package br.edu.infnet.teacherservice.service.impl;

import br.edu.infnet.teacherservice.exception.InvalidTeacherDataException;
import br.edu.infnet.teacherservice.exception.TeacherNotFoundException;
import br.edu.infnet.teacherservice.model.Teacher;
import br.edu.infnet.teacherservice.repository.TeacherRepository;
import br.edu.infnet.teacherservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getTeacherById(String id) {
        return teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> getTeachersByName(String name) {
        return teacherRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public boolean  existsTeacherById(String id) {
        return teacherRepository.existsById(id);
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new InvalidTeacherDataException("Dados inválidos fornecidos para a criação do professor");
        }
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacherById(String id, Teacher teacher) {
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

    @Override
    public void deleteTeacherById(String id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Professor não encontrado para o ID: " + id));
        teacherRepository.delete(teacher);
    }
}
