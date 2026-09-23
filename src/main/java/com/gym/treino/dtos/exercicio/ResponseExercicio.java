package com.gym.treino.dtos.exercicio;

public record ResponseExercicio(
        Long id,
        String nomeExercicio,
        String imagemExercicio
) {
}
