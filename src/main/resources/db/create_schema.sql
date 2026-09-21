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
-- treino.grupo_muscular
-- =========================
CREATE TABLE IF NOT EXISTS treino.grupo_muscular (
    id               BIGSERIAL PRIMARY KEY,
    musculo_afetado  VARCHAR(255)
);

-- =========================
-- treino.exercicio
-- =========================
CREATE TABLE IF NOT EXISTS treino.exercicio (
    id                BIGSERIAL PRIMARY KEY,
    nome_exercicio    VARCHAR(255),
    imagem_exercicio  VARCHAR(255)
);

-- =========================
-- treino.ficha_exercicio
-- =========================
CREATE TABLE IF NOT EXISTS treino.ficha_exercicio (
    id            BIGSERIAL PRIMARY KEY,
    ficha_id      UUID,
    exercicio_id  BIGINT,
    CONSTRAINT fk_ficha_exercicio_ficha
        FOREIGN KEY (ficha_id)
        REFERENCES treino.ficha (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_ficha_exercicio_exercicio
        FOREIGN KEY (exercicio_id)
        REFERENCES treino.exercicio (id)
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_ficha_exercicio_ficha_id ON treino.ficha_exercicio (ficha_id);
CREATE INDEX IF NOT EXISTS idx_ficha_exercicio_exercicio_id ON treino.ficha_exercicio (exercicio_id);

-- =========================
-- treino.serie
-- =========================
CREATE TABLE IF NOT EXISTS treino.serie (
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    numero_serie       SMALLINT,
    numero_repeticoes  SMALLINT,
    carga_exercicio    NUMERIC(6,2),
    tempo_descanso     TIME,
    exercicio_id       BIGINT,
    CONSTRAINT fk_serie_exercicio
        FOREIGN KEY (exercicio_id)
        REFERENCES treino.exercicio (id)
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_serie_exercicio_id ON treino.serie (exercicio_id);
