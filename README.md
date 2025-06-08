## 📦 Endpoints da API ExpedFácil
Url apara documentação: http://localhost:8080/swagger-ui/index.html#/
### 🔧 Carga

| Método | Endpoint         | Descrição                                                    |
|--------|------------------|--------------------------------------------------------------|
| POST   | `/carga`         | Cria uma nova carga com entregas e produtos                 |
| GET    | `/carga`         | Lista todas as cargas cadastradas                           |
| GET    | `/carga/{id}`    | Retorna os detalhes completos de uma carga específica       |
| DELETE | `/carga/{id}`    | Remove uma carga existente pelo número de embarque          |

---

### 📍 Local de Estoque

| Método | Endpoint                                   | Descrição                                                                 |
|--------|--------------------------------------------|---------------------------------------------------------------------------|
| GET    | `/local-estoque`                           | Lista todos os produtos de todas as cargas com cálculo de estoque         |
| GET    | `/local-estoque/carga/{numeroEmbarque}`    | Lista os produtos com cálculo de uma carga específica                     |
| PUT    | `/local-estoque/{id}`                      | Atualiza manualmente o campo `localEstoque` de um produto (texto simples) |

---

### 🧾 Produto (Catálogo)

| Método | Endpoint             | Descrição                                                       |
|--------|----------------------|------------------------------------------------------------------|
| POST   | `/Produto`           | Cadastra um novo produto individual no catálogo                 |
| GET    | `/Produto`           | Lista todos os produtos cadastrados no catálogo                 |
| GET    | `/Produto/{id}`      | Retorna os detalhes de um produto pelo ID                       |
| PUT    | `/Produto/{id}`      | Atualiza um produto existente com base no ID                    |
| DELETE | `/Produto/{id}`      | Remove um produto do catálogo pelo ID                           |








CORRETO - PRODUTO
{
    "id": "006971.93",
    "nome": "MV DIA% 24/200 (ARGENTINA) (CX2)",
    "quantPorCaixa": 24,
    "quantCxFd": 126
}
Lista de produtos necessárioa para a carga:
{
"id": "005603.94",
"nome": "MV ODERICH 6/2000 (CX)",
"quantPorCaixa": 6,
"quantCxFd": 56
}
{
"id": "005403.94",
"nome": "ERV ODERICH 6/2000 (CX)",
"quantPorCaixa": 6,
"quantCxFd": 56
}
{
"id": "005602.94",
"nome": "MV ODERICH 12/500 (CX)",
"quantPorCaixa": 12,
"quantCxFd": 110
}
{
"id": "005402.94",
"nome": "ERV ODERICH 12/500 (CX)",
"quantPorCaixa": 12,
"quantCxFd": 110
}
{
"id": "005601.96",
"nome": "MV ODERICH 24/200 (FD)",
"quantPorCaixa": 24,
"quantCxFd": 140
}
{
"id": "005401.96",
"nome": "ERV ODERICH 24/200 (FD)",
"quantPorCaixa": 24,
"quantCxFd": 140
}


CORRETO - CARGA (EXEMPLO COM 3 ENTREGAS)

{
"numeroEmbarque": "270392",
"destino": "RJ",
"numeroEntregas": 3,
"tipoCarregamento": "BATIDA",
"transportadora": "Rodo WD",
"tipoVeiculo": "BAU",
"placaCavalo": "AVN-8786",
"placasCarreta": [],
"nomeMotorista": "Henrique",
"tipoCarga": "CLIENTE",
"entregas": [
{
"destinatario": "Clientes Itaguai-RJ",
"observacao": "",
"tipoEntrega": "CLIENTE",
"pesoLiquido": 5724.0,
"pesoBruto": 10729.0,
"produtos": [
{
"codigoProduto": "005401.96",
"quantidadeCaixas": 55
},
{
"codigoProduto": "005402.94",
"quantidadeCaixas": 135
},
{
"codigoProduto": "005403.94",
"quantidadeCaixas": 115
},
{
"codigoProduto": "005601.96",
"quantidadeCaixas": 50
},
{
"codigoProduto": "005602.94",
"quantidadeCaixas": 365
},
{
"codigoProduto": "005603.94",
"quantidadeCaixas": 70
}
]
},
{
"destinatario": "Box 34 Comercio",
"observacao": "",
"tipoEntrega": "CLIENTE",
"pesoLiquido": 2880.0,
"pesoBruto": 5541.12,
"produtos": [
{
"codigoProduto": "005402.94",
"quantidadeCaixas": 160
},
{
"codigoProduto": "005602.94",
"quantidadeCaixas": 320
}
]
},
{
"destinatario": "Box 81 Distribuidora",
"observacao": "",
"tipoEntrega": "CLIENTE",
"pesoLiquido": 4320.0,
"pesoBruto": 8311.68,
"produtos": [
{
"codigoProduto": "005402.94",
"quantidadeCaixas": 240
},
{
"codigoProduto": "005602.94",
"quantidadeCaixas": 480
}
]
}
]
}
=======
MongoDB------
POST
 crud-produto

    localhost:8080/arquivo/upload
    Body -> form-data -> file -> arquivo -> description

GET

    desmarca o file ->
    localhost:8080/arquivo/682f7829d3955967e4e366b5 (ID da imagem gerada no postman ou no proprio MongoDB Compass)

