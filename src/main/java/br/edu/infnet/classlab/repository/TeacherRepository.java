package br.edu.infnet.classlab.repository;

import br.edu.infnet.classlab.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
//    List<Teacher> findAll();
//    Optional<Teacher> findById(Long id);
//    Teacher save(Teacher teacher);
//    Teacher updateById(Long id, Teacher teacher);
//    void deleteById(Long id);
}