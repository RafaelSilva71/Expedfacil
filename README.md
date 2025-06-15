
** FOI ADICIONADO DEPENDENCIAS DO CAFFEINE, ATUALIZAR O MAVEM **

Url apara documentação: http://localhost:8080/swagger-ui/index.html#/

## 📦 Endpoints da API ExpedFácil

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







------------
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

MongoDB------
POST
 crud-produto

    localhost:8080/arquivo/upload
    Body -> form-data -> file -> arquivo -> description

GET

    desmarca o file ->
    localhost:8080/arquivo/682f7829d3955967e4e366b5 (ID da imagem gerada no postman ou no proprio MongoDB Compass)

Upload com o número de Embarque

 GET / DELETE

    localhost:8080/arquivo/nota-fiscal/270392

