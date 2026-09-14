package com.gym.treino.service;

import com.gym.treino.repository.FichaRepository;
import org.springframework.stereotype.Service;

@Service
public class FichaService {

    protected final FichaRepository fichaRepository;

    protected FichaService(FichaRepository fichaRepository) {
        this.fichaRepository = fichaRepository;
    }

    
}
