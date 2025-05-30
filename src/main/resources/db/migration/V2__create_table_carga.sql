-- Tabela principal
CREATE TABLE carga (
                       numero_embarque VARCHAR(255) PRIMARY KEY,
                       destino VARCHAR(255),
                       numero_entregas INT,
                       tipo_carregamento VARCHAR(50),
                       transportadora VARCHAR(255),
                       tipo_veiculo VARCHAR(50),
                       placa_cavalo VARCHAR(20),
                       nome_motorista VARCHAR(255),
                       tipo_carga VARCHAR(50),
                       status VARCHAR(50),
                       criado_por VARCHAR(255),
                       data_criacao DATETIME,
                       peso_total_liquido DOUBLE,
                       peso_total_bruto DOUBLE,
                       quantidade_total_caixas INT
);

-- Tabela auxiliar para a lista de placas da carreta
CREATE TABLE carga_placas_carreta (
                                      carga_numero_embarque VARCHAR(255),
                                      placa VARCHAR(20),
                                      PRIMARY KEY (carga_numero_embarque, placa),
                                      FOREIGN KEY (carga_numero_embarque) REFERENCES carga(numero_embarque) ON DELETE CASCADE
);
