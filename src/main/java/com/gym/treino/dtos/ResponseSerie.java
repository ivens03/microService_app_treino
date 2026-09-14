package com.gym.treino.dtos;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.UUID;

public record ResponseSerie(
        UUID id,
        Byte numeroSerie,
        Byte numeroRepeticoes,
        BigDecimal cargaExercicio,
        Time tempoDescanso
) {
}
