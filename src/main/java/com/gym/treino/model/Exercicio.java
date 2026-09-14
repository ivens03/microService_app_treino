package com.gym.treino.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "treino", name = "exercicio")
public class Exercicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "nome_exercicio")
    String nomeExercicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_id")
    Ficha ficha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tecnica_avancada")
    TecnicaAvancada tecnicaAvancada;

    @OneToMany(mappedBy = "exercicio", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Serie> series;
}
