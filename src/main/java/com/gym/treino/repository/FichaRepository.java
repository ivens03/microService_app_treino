package com.gym.treino.repository;

import com.gym.treino.model.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, UUID> {
}
