package br.edu.infnet.classlab.controller;

import br.edu.infnet.classlab.exception.InvalidLessonDataException;
import br.edu.infnet.classlab.exception.InvalidTeacherDataException;
import br.edu.infnet.classlab.exception.LessonNotFoundException;
import br.edu.infnet.classlab.exception.TeacherNotFoundException;
import br.edu.infnet.classlab.model.Teacher;
import br.edu.infnet.classlab.service.TeacherService;
import br.edu.infnet.classlab.service.impl.TeacherServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherServiceImpl teacherService;

    @GetMapping
    @Operation(summary = "Retorna todos os professores", description = "Este endpoint retorna uma lista de todos os professores disponíveis. Se não houver nenhum professor, retornará 404 Not Found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professores retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum professor encontrado")
    })
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        if (teachers.isEmpty()) {
            throw new TeacherNotFoundException("Nenhum professor encontrada");
        }
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um professor por ID", description = "Este endpoint retorna um professor específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new TeacherNotFoundException("Nenhum professor encontrado para o ID: " + id));
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Busca os professor pelo nome", description = "Este endpoint retorna todos professores com base no nome fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professores retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Professores não encontrado")
    })
    public ResponseEntity<List<Teacher>> getTeachersByName(@PathVariable String name) {
        List<Teacher> teachers = teacherService.getTeachersByName(name);
        if(teachers.isEmpty()) {
            throw new TeacherNotFoundException("Nenhum professor encontrado com o nome " + name);
        }

        return ResponseEntity.ok(teachers);
    }

    @PostMapping
    @Operation(summary = "Cria um novo professor", description = "Este endpoint cria um novo professor com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Professor criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a criação do professor"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher newTeacher = teacherService.saveTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTeacher);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um professor existente", description = "Este endpoint atualiza um professor específico com base no ID fornecido e nos novos dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a atualização")
    })
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        try {
            Teacher updatedTeacher = teacherService.updateTeacherById(id, teacher);
            if (updatedTeacher == null) {
                throw new LessonNotFoundException("Aula não encontrada para o ID: " + id);
            }
            return ResponseEntity.ok(updatedTeacher);
        } catch (InvalidLessonDataException ex) {
            throw new InvalidTeacherDataException("Dados inválidos fornecidos para a atualização");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um professor por ID", description = "Este endpoint exclui um professor específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Professor excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    public ResponseEntity<Map<String, String>> deleteTeacherById(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            teacherService.deleteTeacherById(id);
            response.put("message", "Professor excluída com sucesso");
            return ResponseEntity.ok(response);
        } catch (LessonNotFoundException ex) {
            throw new LessonNotFoundException("Professor não encontrada para o ID: " + id);
        }
    }
}
