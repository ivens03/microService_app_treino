package com.gym.treino.dtos.exercicio;

import com.gym.treino.dtos.serie.ResponseSerie;
import com.gym.treino.model.TecnicaAvancada;

import java.util.List;
import java.util.UUID;

public record ResponseExercicio(
        UUID id,
        String nomeExercicio,
        TecnicaAvancada tecnicaAvancada,
        List<ResponseSerie> series
) {
}
