package com.gym.treino.dtos.exercicio;

import com.gym.treino.dtos.serie.RequestSerie;
import com.gym.treino.dtos.serie.ResponseSerie;
import com.gym.treino.model.Exercicio;
import com.gym.treino.model.Serie;

import java.util.List;

public class ExercicioMapper {

    private ExercicioMapper() {
    }

    public static Exercicio paraEntidade(RequestExercicio request) {
        Exercicio exercicio = new Exercicio();
        exercicio.setNomeExercicio(request.nomeExercicio());
        exercicio.setTecnicaAvancada(request.tecnicaAvancada());
        exercicio.setSeries(mapearSeries(request.series(), exercicio));
        return exercicio;
    }

    public static ResponseExercicio paraResponse(Exercicio exercicio) {
        List<ResponseSerie> series = exercicio.getSeries().stream()
                .map(ExercicioMapper::paraResponseSerie)
                .toList();

        return new ResponseExercicio(
                exercicio.getId(),
                exercicio.getNomeExercicio(),
                exercicio.getTecnicaAvancada(),
                series);
    }

    private static List<Serie> mapearSeries(List<RequestSerie> requestSeries, Exercicio exercicio) {
        return requestSeries.stream()
                .map(requestSerie -> paraEntidadeSerie(requestSerie, exercicio))
                .toList();
    }

    private static Serie paraEntidadeSerie(RequestSerie requestSerie, Exercicio exercicio) {
        Serie serie = new Serie();
        serie.setNumeroSerie(requestSerie.numeroSerie());
        serie.setNumeroRepeticoes(requestSerie.numeroRepeticoes());
        serie.setCargaExercicio(requestSerie.cargaExercicio());
        serie.setTempoDescanso(requestSerie.tempoDescanso());
        serie.setExercicio(exercicio);
        return serie;
    }

    private static ResponseSerie paraResponseSerie(Serie serie) {
        return new ResponseSerie(
                serie.getId(),
                serie.getNumeroSerie(),
                serie.getNumeroRepeticoes(),
                serie.getCargaExercicio(),
                serie.getTempoDescanso());
    }
}
