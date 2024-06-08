package br.edu.infnet.classlab.service.impl;

import br.edu.infnet.classlab.exception.InvalidTeacherDataException;
import br.edu.infnet.classlab.exception.TeacherNotFoundException;
import br.edu.infnet.classlab.model.Teacher;
import br.edu.infnet.classlab.repository.TeacherRepository;
import br.edu.infnet.classlab.service.TeacherService;
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
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> getTeachersByName(String name) {
        return teacherRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public boolean  existsTeacherById(Long id) {
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

    @Override
    public void deleteTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Professor não encontrado para o ID: " + id));
        teacherRepository.delete(teacher);
    }
}
