package com.gym.treino.service;

import com.gym.treino.dtos.exercicio.ExercicioMapper;
import com.gym.treino.dtos.exercicio.RequestExercicio;
import com.gym.treino.dtos.exercicio.ResponseExercicio;
import com.gym.treino.model.Exercicio;
import com.gym.treino.repository.ExercicioRepository;
import org.springframework.stereotype.Service;

@Service
public class ExercicioService {

    protected final ExercicioRepository exercicioRepository;

    protected ExercicioService(ExercicioRepository exercicioRepository) {
        this.exercicioRepository = exercicioRepository;
    }

    public ResponseExercicio criarExercicio(RequestExercicio request) {
        Exercicio exercicio = ExercicioMapper.paraEntidade(request);

        exercicioRepository.save(exercicio);

        return ExercicioMapper.paraResponse(exercicio);
    }
}
