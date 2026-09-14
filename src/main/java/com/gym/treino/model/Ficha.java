package com.gym.treino.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(schema = "treino", name = "ficha")
@Schema(description = "Dentro de schema: **treino**. Armazenamos a ficha do usuario.")
@Tag(name = "treino")
public class Ficha implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "treino_do_dia")
    String treinoDoDia;

    @Column(name = "data_inicio")
    Date dataInicio;

    @Column(name = "data_troca")
    Date dataTroca;

    @OneToMany(mappedBy = "ficha", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Exercicio> exercicios;
}
