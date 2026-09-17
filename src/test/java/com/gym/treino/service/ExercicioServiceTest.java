package com.gym.treino.service;

import com.gym.treino.dtos.exercicio.RequestExercicio;
import com.gym.treino.dtos.serie.RequestSerie;
import com.gym.treino.dtos.exercicio.ResponseExercicio;
import com.gym.treino.model.Exercicio;
import com.gym.treino.model.TecnicaAvancada;
import com.gym.treino.repository.ExercicioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExercicioServiceTest {

    @Mock
    ExercicioRepository exercicioRepository;

    @InjectMocks
    ExercicioService exercicioService;

    @Test
    void deveCriarExercicioComSuasSeries() {
        when(exercicioRepository.save(any(Exercicio.class))).thenAnswer(invocation -> invocation.getArgument(0));

        RequestSerie requestSerie = new RequestSerie((byte) 1, (byte) 10, BigDecimal.valueOf(80), null);
        RequestExercicio request = new RequestExercicio("Supino reto", TecnicaAvancada.NORMAL, List.of(requestSerie));

        ResponseExercicio response = exercicioService.criarExercicio(request);

        assertThat(response.nomeExercicio()).isEqualTo("Supino reto");
        assertThat(response.tecnicaAvancada()).isEqualTo(TecnicaAvancada.NORMAL);
        assertThat(response.series()).hasSize(1);
        assertThat(response.series().get(0).numeroSerie()).isEqualTo((byte) 1);
        assertThat(response.series().get(0).cargaExercicio()).isEqualByComparingTo(BigDecimal.valueOf(80));

        verify(exercicioRepository).save(any(Exercicio.class));
    }
}
