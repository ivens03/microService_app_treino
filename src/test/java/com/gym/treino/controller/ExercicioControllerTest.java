package com.gym.treino.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.treino.dtos.exercicio.RequestExercicio;
import com.gym.treino.dtos.exercicio.ResponseExercicio;
import com.gym.treino.dtos.serie.RequestSerie;
import com.gym.treino.dtos.serie.ResponseSerie;
import com.gym.treino.model.TecnicaAvancada;
import com.gym.treino.service.ExercicioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.matchesPattern;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExercicioController.class)
class ExercicioControllerTest {

    @Autowired
    MockMvc mvc;

    final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    ExercicioService exercicioService;

    @Test
    void deveRetornar201AoCadastrarExercicio() throws Exception {
        RequestSerie requestSerie = new RequestSerie((byte) 1, (byte) 10, BigDecimal.valueOf(80), null);
        RequestExercicio request = new RequestExercicio("Supino reto", TecnicaAvancada.NORMAL, List.of(requestSerie));

        UUID id = UUID.randomUUID();
        ResponseSerie responseSerie = new ResponseSerie(UUID.randomUUID(), (byte) 1, (byte) 10, BigDecimal.valueOf(80), null);
        ResponseExercicio response = new ResponseExercicio(id, "Supino reto", TecnicaAvancada.NORMAL, List.of(responseSerie));

        when(exercicioService.criarExercicio(any(RequestExercicio.class))).thenReturn(response);

        mvc.perform(post("/api/exercicios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.nomeExercicio").value("Supino reto"));
    }

    @Test
    void deveRetornar400QuandoNomeExercicioEstiverEmBranco() throws Exception {
        RequestSerie requestSerie = new RequestSerie((byte) 1, (byte) 10, BigDecimal.valueOf(80), null);
        RequestExercicio request = new RequestExercicio("", TecnicaAvancada.NORMAL, List.of(requestSerie));

        mvc.perform(post("/api/exercicios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornar400ComDetalheDoCampoQuandoNomeExercicioEstiverAusente() throws Exception {
        String jsonSemNomeExercicio = """
                {
                    "tecnicaAvancada": "NORMAL",
                    "series": [
                        { "numeroSerie": 1, "numeroRepeticoes": 10, "cargaExercicio": 40.0, "tempoDescanso": "00:01:30" }
                    ]
                }
                """;

        mvc.perform(post("/api/exercicios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSemNomeExercicio))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.path").value("/api/exercicios"))
                .andExpect(jsonPath("$.timestamp").value(matchesPattern("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}")))
                .andExpect(jsonPath("$.campos[0].campo").value("nomeExercicio"))
                .andExpect(jsonPath("$.campos[0].mensagem").value("nome do exercício é obrigatório"));
    }
}
