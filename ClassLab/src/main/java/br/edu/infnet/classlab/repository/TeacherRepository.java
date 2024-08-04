package br.edu.infnet.classlab.repository;

import br.edu.infnet.classlab.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByNameContainingIgnoreCase(String teacherName);
}