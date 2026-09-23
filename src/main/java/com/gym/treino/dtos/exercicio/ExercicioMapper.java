package com.gym.treino.dtos.exercicio;

import com.gym.treino.model.Exercicio;

public class ExercicioMapper {

    private ExercicioMapper() {
    }

    public static Exercicio paraEntidade(RequestExercicio request) {
        Exercicio exercicio = new Exercicio();
        exercicio.setNomeExercicio(request.nomeExercicio());
        exercicio.setImagemExercicio(request.imagemExercicio());
        return exercicio;
    }

    public static ResponseExercicio paraResponse(Exercicio exercicio) {
        return new ResponseExercicio(
                exercicio.getId(),
                exercicio.getNomeExercicio(),
                exercicio.getImagemExercicio());
    }
}
