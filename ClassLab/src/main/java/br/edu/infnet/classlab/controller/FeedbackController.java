package br.edu.infnet.classlab.controller;

import br.edu.infnet.classlab.model.Feedback;
import br.edu.infnet.classlab.service.FeedbackService;
import br.edu.infnet.classlab.service.impl.FeedbackServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/lesson/feedbacks")
public class FeedbackController {
    private final FeedbackServiceImpl feedbackService;

    @GetMapping("/{lessonId}")
    @Operation(summary = "Recupera todos os feedbacks de uma aula específica",
            description = "Este endpoint recupera todos os feedbacks associados a um determinado ID de aula.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Feedbacks recuperados com sucesso"),
            @ApiResponse(responseCode = "400", description = "ID da aula inválido fornecido"),
            @ApiResponse(responseCode = "404", description = "Nenhum feedback encontrado para o ID de aula fornecido")
    })
    public ResponseEntity<List<Feedback>> getAllFeedbacksByLessonId(@PathVariable Long lessonId) {
        log.info("Get all feedbacks by lessonId: {}", lessonId);
        List<Feedback> payload = feedbackService.getAllFeedbacksByLessonId(lessonId);
        if (payload == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(payload);
    }
}
