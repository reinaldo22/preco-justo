CREATE TABLE "public".pato (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) UNIQUE NOT NULL,
    mae_id INT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    profundidade NUMERIC(10,5),
    venda_id INT NULL,
    FOREIGN KEY (mae_id) REFERENCES pato(id) ON DELETE SET NULL,
    FOREIGN KEY (venda_id) REFERENCES venda_patos(id) ON DELETE CASCADE
);