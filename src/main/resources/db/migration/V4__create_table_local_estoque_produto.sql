CREATE TABLE local_estoque_produto (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       numero_embarque VARCHAR(255),
                                       codigo_produto VARCHAR(100),
                                       nome_produto VARCHAR(255),
                                       resultado_calculo VARCHAR(255),
                                       local_estoque VARCHAR(255),
                                       CONSTRAINT fk_local_estoque_carga FOREIGN KEY (numero_embarque)
                                           REFERENCES carga(numero_embarque) ON DELETE CASCADE
);
