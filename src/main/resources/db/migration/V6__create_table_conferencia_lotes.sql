-- Tabela principal da conferência
CREATE TABLE IF NOT EXISTS conferencia_lotes (
                                                 id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                                 numero_embarque_original VARCHAR(255) NOT NULL UNIQUE,
    data_registro DATETIME
    );

-- Entrega conferida vinculada à conferência
CREATE TABLE IF NOT EXISTS entrega_conferida (
                                                 id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                                 numero_entrega INT NOT NULL,
                                                 conferencia_id BIGINT NOT NULL,
                                                 CONSTRAINT fk_entrega_conferencia FOREIGN KEY (conferencia_id) REFERENCES conferencia_lotes(id) ON DELETE CASCADE
    );

-- Produto conferido vinculado à entrega
CREATE TABLE IF NOT EXISTS produto_conferido (
                                                 id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                                 codigo_produto VARCHAR(255) NOT NULL,
    nome_produto VARCHAR(255) NOT NULL,
    quantidade_total INT NOT NULL,
    entrega_id BIGINT NOT NULL,
    CONSTRAINT fk_produto_entrega FOREIGN KEY (entrega_id) REFERENCES entrega_conferida(id) ON DELETE CASCADE
    );

-- Lote conferido vinculado ao produto
CREATE TABLE IF NOT EXISTS lote_conferido (
                                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                              lote VARCHAR(255) NOT NULL,
    validade VARCHAR(20) NOT NULL,
    quantidade INT NOT NULL,
    data_producao VARCHAR(20),
    produto_id BIGINT NOT NULL,
    CONSTRAINT fk_lote_produto FOREIGN KEY (produto_id) REFERENCES produto_conferido(id) ON DELETE CASCADE
    );
