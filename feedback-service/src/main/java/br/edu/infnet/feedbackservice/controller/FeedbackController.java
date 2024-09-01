package br.edu.infnet.feedbackservice.controller;

import br.edu.infnet.feedbackservice.model.Classification;
import br.edu.infnet.feedbackservice.model.Feedback;
import br.edu.infnet.feedbackservice.model.Lesson;
import br.edu.infnet.feedbackservice.service.FeedbackResponse;
import br.edu.infnet.feedbackservice.service.impl.FeedbackServiceImpl;
import br.edu.infnet.feedbackservice.service.impl.LessonServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
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
    public ResponseEntity<?> saveFeedback(@RequestBody Feedback feedback, @RequestParam String classification) throws JsonProcessingException {
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
        int totalFeedback = feedbackService.getFeedbackCountByLessonId(feedback.getLessonId());
        feedback.setTotalFeedbacks(totalFeedback+1);
        Feedback feedbackSaved = feedbackService.saveFeedback(feedback);
        feedbackService.sendFeedback(feedbackSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackSaved);
    }

    @GetMapping("/lesson/{lessonId}")
    @Operation(summary = "Recupera todos os feedbacks de uma aula específica",
            description = "Este endpoint recupera todos os feedbacks associados a um ID de aula específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Feedbacks recuperados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum feedback encontrado para o ID de aula fornecido")
    })
    public ResponseEntity<FeedbackResponse> getAllFeedbacksByLessonId(@PathVariable String lessonId) {
        log.info("Get Feedback by Lesson ID: {}", lessonId);
        Feedback[] feedbacks = feedbackService.getAllFeedbacksByLessonId(lessonId);
        FeedbackResponse feedbackResponse = new FeedbackResponse(Arrays.asList(feedbacks));
        return feedbacks.length == 0 ? ResponseEntity.notFound().build() : ResponseEntity.ok(feedbackResponse);
    }
}
