# FlowNotify 🛍️📨

Bem-vindo ao FlowNotify, um projeto de exemplo que demonstra uma arquitetura de microsserviços reativa para um sistema
de compras, utilizando Java, Spring Boot, e Apache Kafka.

## Visão Geral da Arquitetura

O FlowNotify é composto por dois microsserviços principais que se comunicam de forma assíncrona para processar pedidos e
notificar os usuários.

O fluxo de uma nova compra é o seguinte:

Cliente ➡️ [POST /pedidos] ➡️ [Serviço de Compras] ➡️ [Salva no PostgreSQL] ➡️ [Publica Evento no Kafka]
➡️ [Serviço de Notificação] ➡️ [Envia E-mail de Confirmação]

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3.x
* **Comunicação Assíncrona:** Spring Cloud Stream com Apache Kafka
* **Banco de Dados:** PostgreSQL
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Versionamento de Banco:** Flyway
* **Build Tool:** Maven (Projeto Multi-Módulo)

## 📦 Módulos (Microsserviços)

O projeto é organizado em uma estrutura Maven multi-módulo:

### 1. `servico-compras` (Producer)

- **Responsabilidades:**
    - Expor uma API REST para receber novos pedidos (`POST /pedidos`).
    - Validar e persistir os dados do pedido no banco de dados PostgreSQL.
    - Publicar um evento `PedidoSalvoEvent` em um tópico do Kafka após cada compra bem-sucedida.
- **Porta:** `8080`

### 2. `servico-notificacao` (Consumer)

- **Responsabilidades:**
    - Ouvir o tópico do Kafka para consumir os eventos `PedidoSalvoEvent`.
    - Processar o evento e utilizar o `spring-boot-starter-mail` para enviar um e-mail de confirmação para o usuário.
    - É desacoplado e não possui conhecimento sobre a lógica de negócio do serviço de compras.
- **Porta:** `8081`

## 🛠️ Pré-requisitos

Antes de começar, garanta que você tenha as seguintes ferramentas instaladas:

* JDK 17 ou superior
* Apache Maven 3.8+
* Docker e Docker Compose
* Um cliente de API como Postman ou Insomnia (para testar o endpoint)

📡 Testando a API
Com ambos os serviços e a infraestrutura rodando, você pode enviar uma requisição POST para o servico-compras para
simular um novo pedido.

Endpoint: POST http://localhost:8080/pedidos

Corpo da Requisição (JSON):

```
JSON

{
   "idUsuario": 101,
   "valorTotal": 529.80,
    "itens": [
   {
   "idProduto": "TEC-00123",
    "quantidade": 1,
    "precoUnitario": 350.00
   },
   {
    "idProduto": "MSE-00456",
    "quantidade": 2,
     "precoUnitario": 89.90
    }
  ]
}
```

O que esperar:

Você receberá uma resposta 201 Created com o corpo do pedido salvo.

No console do servico-compras, você verá logs indicando o salvamento no banco e o envio da mensagem para o Kafka.

No console do servico-notificacao, você verá logs indicando o recebimento da mensagem e a tentativa de envio do e-mail.