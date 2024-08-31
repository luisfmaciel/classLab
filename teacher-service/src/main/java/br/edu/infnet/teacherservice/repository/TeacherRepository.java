package br.edu.infnet.teacherservice.repository;

import br.edu.infnet.teacherservice.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeacherRepository extends MongoRepository<Teacher, String> {
    List<Teacher> findByNameContainingIgnoreCase(String teacherName);
}