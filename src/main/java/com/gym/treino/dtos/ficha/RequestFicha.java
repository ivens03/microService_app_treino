package com.gym.treino.dtos.ficha;

import com.gym.treino.dtos.exercicio.RequestExercicio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;
import java.util.List;

public record RequestFicha(
        @Valid
        @NotBlank(message = "treino do dia é obrigatório")
        String treinoDoDia,

        @Valid
        @NotNull(message = "data de início é obrigatória")
        @PastOrPresent(message = "data de início deve ser a data atual")
        Date dataInicio,

        Date dataTroca,

        @Valid
        @NotEmpty(message = "ficha deve ter ao menos um exercício")
        List<RequestExercicio> exercicios
) {
}