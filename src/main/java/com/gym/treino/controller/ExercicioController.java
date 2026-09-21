package com.gym.treino.controller;

import com.gym.treino.dtos.exercicio.RequestExercicio;
import com.gym.treino.dtos.exercicio.ResponseExercicio;
import com.gym.treino.service.ExercicioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercicios")
public class ExercicioController {

    private final ExercicioService exercicioService;

    public ExercicioController(ExercicioService exercicioService) {
        this.exercicioService = exercicioService;
    }

    @PostMapping
    public ResponseEntity<ResponseExercicio> cadastrar(@Valid @RequestBody RequestExercicio request) {
        ResponseExercicio response = exercicioService.criarExercicio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
