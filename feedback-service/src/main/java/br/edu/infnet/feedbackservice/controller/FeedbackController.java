package br.edu.infnet.feedbackservice.controller;

import br.edu.infnet.feedbackservice.model.Classification;
import br.edu.infnet.feedbackservice.model.Feedback;
import br.edu.infnet.feedbackservice.model.Lesson;
import br.edu.infnet.feedbackservice.service.impl.FeedbackServiceImpl;
import br.edu.infnet.feedbackservice.service.impl.LessonServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
@Slf4j
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackServiceImpl feedbackService;
    private final LessonServiceImpl lessonService;

    @PostMapping
    @Operation(summary = "Salva um feedback",
            description = "Este endpoint salva um feedback associado a uma aula, recebendo o feedback e a classificação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Feedback salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Classificação inválida ou aula não encontrada"),
            @ApiResponse(responseCode = "404", description = "Aula não encontrada")
    })
    public ResponseEntity<?> saveFeedback(@RequestBody Feedback feedback, @RequestParam String classification) {
        log.info("Save Feedback: {}", feedback);
        log.info("Classificatin: {}", classification);
        Lesson lesson = lessonService.getLessonById(feedback.getLessonId());
        if (lesson == null) {
            return ResponseEntity.notFound().build();
        }
        if (!Feedback.isValidClassification(classification)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid classification"));
        }
        feedback.setClassification(Classification.valueOf(classification));
        Feedback feedbackSaved = feedbackService.saveFeedback(feedback);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackSaved);
    }

    @GetMapping("/lessons/{lessonId}")
    @Operation(summary = "Recupera todos os feedbacks de uma aula específica",
            description = "Este endpoint recupera todos os feedbacks associados a um ID de aula específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Feedbacks recuperados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum feedback encontrado para o ID de aula fornecido")
    })
    public ResponseEntity<?> getAllFeedbacksByLessonId(@PathVariable Long lessonId) {
        log.info("Get Feedback by Lesson ID: {}", lessonId);
        List<Feedback> feedbacks = feedbackService.getAllFeedbacksByLessonId(lessonId);
        if (feedbacks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedbacks);
    }
}
