CREATE TABLE palavras (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    termo VARCHAR(255) NOT NULL
);

CREATE TABLE etiquetas (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Tabela muitos-para-muitos intermediária
CREATE TABLE palavra_etiqueta (
    palavra_id BIGINT NOT NULL,
    etiqueta_id BIGINT NOT NULL,
    PRIMARY KEY (palavra_id, etiqueta_id),
    CONSTRAINT fk_palavra
        FOREIGN KEY (palavra_id)
        REFERENCES palavras (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_etiqueta
        FOREIGN KEY (etiqueta_id)
        REFERENCES etiquetas (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);