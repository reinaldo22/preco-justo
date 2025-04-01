CREATE TABLE "public".vendedor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    matricula VARCHAR(20) UNIQUE NOT NULL,
    status BOOLEAN DEFAULT true,
	createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);