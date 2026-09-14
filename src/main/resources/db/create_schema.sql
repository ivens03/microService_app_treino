CREATE SCHEMA IF NOT EXISTS treino;

-- =========================
-- treino.ficha
-- =========================
CREATE TABLE IF NOT EXISTS treino.ficha (
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    treino_do_dia VARCHAR(255),
    data_inicio   TIMESTAMP,
    data_troca    TIMESTAMP
);

-- =========================
-- treino.exercicio
-- =========================
CREATE TABLE IF NOT EXISTS treino.exercicio (
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome_exercicio    VARCHAR(255),
    ficha_id          UUID,
    tecnica_avancada  VARCHAR(30),
    CONSTRAINT fk_exercicio_ficha
        FOREIGN KEY (ficha_id)
        REFERENCES treino.ficha (id)
        ON DELETE CASCADE,
    CONSTRAINT ck_exercicio_tecnica_avancada
        CHECK (tecnica_avancada IN (
            'NORMAL',
            'DROP_SET',
            'REST_PAUSE',
            'PONTO_ZERO',
            'PIRAMIDE_CRESCENTE',
            'PIRAMIDE_DECRESCENTE'
        ))
);

CREATE INDEX IF NOT EXISTS idx_exercicio_ficha_id ON treino.exercicio (ficha_id);

-- =========================
-- treino.serie
-- =========================
CREATE TABLE IF NOT EXISTS treino.serie (
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    numero_serie       SMALLINT,
    numero_repeticoes  SMALLINT,
    carga_exercicio    NUMERIC(6,2),
    tempo_descanso     TIME,
    exercicio_id       UUID,
    CONSTRAINT fk_serie_exercicio
        FOREIGN KEY (exercicio_id)
        REFERENCES treino.exercicio (id)
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_serie_exercicio_id ON treino.serie (exercicio_id);
