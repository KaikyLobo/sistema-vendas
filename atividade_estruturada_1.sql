CREATE TABLE fornecedor (
                            id_fornecedor SERIAL PRIMARY KEY,
                            nome VARCHAR(100) NOT NULL,
                            telefone VARCHAR(20)
);

ALTER TABLE produtos
    ADD COLUMN id_fornecedor INTEGER;

ALTER TABLE produtos
    ADD CONSTRAINT fk_produtos_fornecedor
        FOREIGN KEY (id_fornecedor)
            REFERENCES fornecedor(id_fornecedor)
            ON DELETE RESTRICT;

-- ON DELETE RESTRICT impede a exclusão de um fornecedor
-- enquanto existirem produtos relacionados a ele.