package com.gym.treino.dtos.ficha;

import com.gym.treino.dtos.exercicio.ResponseExercicio;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record ResponseFicha(
        UUID id,
        String treinoDoDia,
        Date dataInicio,
        Date dataTroca,
        List<ResponseExercicio> exercicios
) {
}
