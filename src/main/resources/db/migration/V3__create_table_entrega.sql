CREATE TABLE entrega (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         numero_entrega INT,
                         destinatario VARCHAR(255),
                         observacao TEXT,
                         peso_liquido DOUBLE,
                         peso_bruto DOUBLE,
                         quantidade_total_caixas INT,
                         tipo_entrega VARCHAR(50),
                         carga_id VARCHAR(255),
                         FOREIGN KEY (carga_id) REFERENCES carga(numero_embarque) ON DELETE CASCADE
);
