package com.gym.treino.dtos;

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
