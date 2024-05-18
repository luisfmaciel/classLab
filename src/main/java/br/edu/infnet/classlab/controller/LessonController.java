package br.edu.infnet.classlab.controller;

import br.edu.infnet.classlab.model.Lesson;
import br.edu.infnet.classlab.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return lessons.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lessons);
    }

    @PostMapping
    @Operation(summary = "Cria uma nova aula", description = "Este endpoint cria uma nova aula com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aula criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a criação da aula")
    })
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        Lesson newLesson = lessonService.saveLesson(lesson);
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
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma aula existente", description = "Este endpoint atualiza uma aula específica com base no ID fornecido e nos novos dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aula atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aula não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a atualização")
    })
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson updatedLesson) {
        Lesson lesson = lessonService.updateLesson(id, updatedLesson);
        return ResponseEntity.ok(lesson);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma aula por ID", description = "Este endpoint exclui uma aula específica com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aula excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aula não encontrada")
    })
    public ResponseEntity<Lesson> deleteLessonById(@PathVariable Long id) {
        lessonService.deleteLessonById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assign-teacher")
    @Operation(summary = "Associa um professor a uma aula", description = "Este endpoint associa um professor específico a uma aula específica com base nos IDs fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor associado à aula com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aula ou professor não encontrados"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a associação")
    })
    public ResponseEntity<Lesson> assignTeacherToLesson(@RequestParam Long lessonId, @RequestParam Long teacherId) {
        Lesson assignedLesson = lessonService.assignTeacherToLesson(lessonId, teacherId);
        return ResponseEntity.ok(assignedLesson);
    }
}
