package com.gym.treino.dtos.exercicio;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record RequestExercicio(
        @Valid
        @NotBlank(message = "nome do exercício é obrigatório")
        String nomeExercicio,

        String imagemExercicio
) {
}