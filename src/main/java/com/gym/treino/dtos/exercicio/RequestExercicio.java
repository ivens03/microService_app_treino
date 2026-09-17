package com.gym.treino.dtos.exercicio;

import com.gym.treino.dtos.serie.RequestSerie;
import com.gym.treino.model.TecnicaAvancada;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestExercicio(
        @Valid
        @NotBlank(message = "nome do exercício é obrigatório")
        String nomeExercicio,

        @NotNull(message = "Deve colocar se a uma técnica avançada")
        TecnicaAvancada tecnicaAvancada,

        @Valid
        @NotEmpty(message = "exercício deve ter ao menos uma série")
        List<RequestSerie> series
) {
}