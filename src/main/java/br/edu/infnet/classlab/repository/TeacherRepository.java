package br.edu.infnet.classlab.repository;

import br.edu.infnet.classlab.model.Lesson;
import br.edu.infnet.classlab.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TeacherRepository extends JpaRepository<Teacher, Long> { }