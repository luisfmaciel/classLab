package br.edu.infnet.classlab.controller;

import br.edu.infnet.classlab.exception.InvalidLessonDataException;
import br.edu.infnet.classlab.exception.LessonNotFoundException;
import br.edu.infnet.classlab.model.Lesson;
import br.edu.infnet.classlab.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping
    @Operation(summary = "Retorna todas as aulas", description = "Este endpoint retorna uma lista de todas as aulas disponíveis. Se não houver nenhuma aula, retornará 404 Not Found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aulas retornadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma aula encontrada")
    })
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonService.getAllLessons();
        if (lessons.isEmpty()) {
            throw new LessonNotFoundException("Nenhuma aula encontrada");
        }
        return ResponseEntity.ok(lessons);
    }

    @PostMapping
    @Operation(summary = "Cria uma nova aula", description = "Este endpoint cria uma nova aula com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aula criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a criação da aula"),
            @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson, @RequestParam Long teacherId) {
        Lesson newLesson = lessonService.saveLesson(lesson, teacherId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLesson);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma aula por ID", description = "Este endpoint retorna uma aula específica com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aula não encontrada")
    })
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new LessonNotFoundException("Aula não encontrada para o ID: " + id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma aula existente", description = "Este endpoint atualiza uma aula específica com base no ID fornecido e nos novos dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aula não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a atualização")
    })
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson updatedLesson) {
        try {
            Lesson lesson = lessonService.updateLesson(id, updatedLesson);
            if (lesson == null) {
                throw new LessonNotFoundException("Aula não encontrada para o ID: " + id);
            }
            return ResponseEntity.ok(lesson);
        } catch (InvalidLessonDataException ex) {
            throw new InvalidLessonDataException("Dados inválidos fornecidos para a atualização");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma aula por ID", description = "Este endpoint exclui uma aula específica com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aula excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aula não encontrada")
    })
    public ResponseEntity<Map<String, String>> deleteLessonById(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            lessonService.deleteLessonById(id);
            response.put("message", "Aula excluída com sucesso");
            return ResponseEntity.ok(response);
        } catch (LessonNotFoundException ex) {
            throw new LessonNotFoundException("Aula não encontrada para o ID: " + id);
        }
    }
}
