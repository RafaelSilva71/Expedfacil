CREATE TABLE avaria_carga (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              numero_embarque VARCHAR(255) NOT NULL,
                              data_registro DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE produto_avariado (
                                  id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                  codigo_produto VARCHAR(255) NOT NULL,
                                  nome_produto VARCHAR(255),
                                  lote VARCHAR(255) NOT NULL,
                                  quantidade INT NOT NULL,
                                  motivo VARCHAR(255) NOT NULL,
                                  observacao TEXT,
                                  avaria_carga_id BIGINT,
                                  CONSTRAINT fk_avaria_carga
                                      FOREIGN KEY (avaria_carga_id)
                                          REFERENCES avaria_carga(id)
                                          ON DELETE CASCADE
);
