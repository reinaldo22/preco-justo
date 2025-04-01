CREATE TABLE "public".venda_patos (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    vendedor_id INT NOT NULL,
    data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total NUMERIC(10,2) NOT NULL,
    desconto_aplicado NUMERIC(10,2) DEFAULT 0,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE RESTRICT,
    FOREIGN KEY (vendedor_id) REFERENCES vendedor(id) ON DELETE RESTRICT
);