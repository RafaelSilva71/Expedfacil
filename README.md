# Sistema de Conferência de Cargas - ExpedFácil 🚛📦

Sistema desenvolvido para automatizar e digitalizar o processo de conferência de cargas na expedição da empresa **Oderich – Unidade Orizona**, substituindo controles manuais em papel por uma solução eficiente, rastreável e segura.

## 📌 Objetivo Geral

Automatizar os processos da expedição, garantindo:

- Redução de erros
- Maior agilidade
- Rastreabilidade das operações
- Segurança e integridade das informações

## 👥 Público Alvo

- Encarregados de expedição  
- Estoquistas  
- Conferentes  
- Operadores de empilhadeira  
- Supervisores e visualizadores

Todos os colaboradores da expedição poderão interagir com o sistema, via computadores ou tablets (Android), conforme seu perfil de acesso.

## ⚙️ Principais Funcionalidades

- Definição de tipo de carregamento
- Cálculo automático de paletes e sobras
- Registro de avarias com foto
- Captura de fotos da carga 
- Anexação e verificação de notas fiscais via foto
- Histórico completo por etapa 



## 🚧 Possíveis Desafios

- Treinamento dos usuários
- Necessidade de infraestrutura (rede + dispositivos)
- Manutenção contínua e suporte técnico

## 👨‍💻 Equipe de Desenvolvimento

| Nome                      | Função                                               
|---------------------------|---------------------------------------------------------------------|
| **Ricardo Issa de Sousa** | Desenvolvedor                       
| **Erick Gonçalves Maia**  | Desenvolvedor    
| **Rafael de Oliveira**    | Desenvolvedor                             

### Metodologia: Scrum

O desenvolvimento seguiu a abordagem ágil Scrum, com sprints curtos, entregas contínuas e foco na validação progressiva com o cliente.




## 📝 Licença

Este projeto é acadêmico e sem fins lucrativos. Todos os direitos reservados ao IF Goiano - Campus Urutaí.



Url apara documentação: http://localhost:8080/swagger-ui/index.html#/

## MongoDB
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

## 📎 Clone o Projeto

Você pode clonar o projeto diretamente em sua máquina com o comando abaixo:

```bash
git clone https://github.com/seu-usuario/nome-do-repositorio.git


