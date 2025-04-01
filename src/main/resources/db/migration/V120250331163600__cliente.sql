CREATE TABLE "public".cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    iselegivel boolean DEFAULT false,
    status BOOLEAN DEFAULT TRUE
);