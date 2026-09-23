package com.gym.treino.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.treino.dtos.exercicio.RequestExercicio;
import com.gym.treino.dtos.exercicio.ResponseExercicio;
import com.gym.treino.service.ExercicioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

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
        RequestExercicio request = new RequestExercicio("Supino reto", "http://imagem.com/supino.png");

        Long id = 1L;
        ResponseExercicio response = new ResponseExercicio(id, "Supino reto", "http://imagem.com/supino.png");

        when(exercicioService.criarExercicio(any(RequestExercicio.class))).thenReturn(response);

        mvc.perform(post("/api/exercicios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nomeExercicio").value("Supino reto"));
    }

    @Test
    void deveRetornar400QuandoNomeExercicioEstiverEmBranco() throws Exception {
        RequestExercicio request = new RequestExercicio("", null);

        mvc.perform(post("/api/exercicios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornar400ComDetalheDoCampoQuandoNomeExercicioEstiverAusente() throws Exception {
        String jsonSemNomeExercicio = """
                {
                    "imagemExercicio": "http://imagem.com/supino.png"
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
