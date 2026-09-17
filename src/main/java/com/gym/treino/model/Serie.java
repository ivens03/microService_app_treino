package com.gym.treino.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.UUID;

@Entity
@Table(schema = "treino", name = "serie")
@Getter
@Setter
public class Serie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "numero_serie")
    Byte numeroSerie;

    @Column(name = "numero_repeticoes")
    Byte numeroRepeticoes;

    @Column(name = "carga_exercicio")
    BigDecimal cargaExercicio;

    @Column(name = "tempo_descanso")
    Time tempoDescanso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercicio_id")
    Exercicio exercicio;
}
