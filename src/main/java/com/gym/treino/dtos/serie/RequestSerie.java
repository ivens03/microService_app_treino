package com.gym.treino.dtos.serie;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.sql.Time;

public record RequestSerie(

        @Valid
        @NotNull(message = "número da série é obrigatório")
        @Min(value = 1, message = "número da série deve ser maior que zero")
        Byte numeroSerie,

        @Valid
        @NotNull(message = "número de repetições é obrigatório")
        @Min(value = 1, message = "número de repetições deve ser maior que zero")
        Byte numeroRepeticoes,

        @DecimalMin(value = "0.0", message = "carga não pode ser negativa")
        BigDecimal cargaExercicio,

        Time tempoDescanso
) {
}
