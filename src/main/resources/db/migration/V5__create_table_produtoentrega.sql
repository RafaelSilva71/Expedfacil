CREATE TABLE produto_entrega (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 codigo_produto VARCHAR(100),
                                 nome_produto VARCHAR(255),
                                 quantidade_caixas INT,
                                 entrega_id BIGINT,
                                 CONSTRAINT fk_produto_entrega_entrega FOREIGN KEY (entrega_id)
                                     REFERENCES entrega(id) ON DELETE CASCADE
);
