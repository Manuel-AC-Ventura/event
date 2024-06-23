# API de Gerenciamento de Eventos

## Visão Geral

Esta API permite gerenciar eventos fornecendo funcionalidades para criar, ler, atualizar e excluir registros de eventos. Abaixo está uma descrição de cada rota disponível e exemplos de como usá-las.

## Configuração do Banco de Dados

Certifique-se de configurar as variáveis de ambiente do banco de dados no arquivo `application.properties`, localizado em `src/main/resources`. Exemplo de configuração:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/events
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Substitua `seu_usuario` e `sua_senha` pelas credenciais apropriadas do seu banco de dados.

## Rotas

### 1. Obter Evento por ID

**Endpoint:** `/api/event/get/{id}`  
**Método:** `GET`  
**Descrição:** Recupera um evento específico pelo seu ID.

**Exemplo de Requisição:**
```http
GET /api/event/get/3fa85f64-5717-4562-b3fc-2c963f66afa6
```

**Exemplo de Resposta:**
```json
{
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "title": "Tech Conference 2023",
    "details": "A comprehensive conference covering all aspects of technology.",
    "slug": "tech-conference-2023",
    "maxAttendees": 500,
    "startDate": "2023-10-15T09:00:00",
    "endDate": "2023-10-17T17:00:00",
    "location": "Convention Center, Tech City"
}
```

### 2. Obter Todos os Eventos

**Endpoint:** `/api/event/all`  
**Método:** `GET`  
**Descrição:** Recupera uma lista de todos os eventos.

**Exemplo de Requisição:**
```http
GET /api/event/all
```

**Exemplo de Resposta:**
```json
[
    {
        "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "title": "Tech Conference 2023",
        "details": "A comprehensive conference covering all aspects of technology.",
        "slug": "tech-conference-2023",
        "maxAttendees": 500,
        "startDate": "2023-10-15T09:00:00",
        "endDate": "2023-10-17T17:00:00",
        "location": "Convention Center, Tech City"
    },
    {
        "id": "4da85f64-5717-4562-b3fc-2c963f66afa7",
        "title": "Health Tech Summit",
        "details": "Innovations in healthcare technology.",
        "slug": "health-tech-summit",
        "maxAttendees": 300,
        "startDate": "2023-11-01T09:00:00",
        "endDate": "2023-11-03T17:00:00",
        "location": "Health City Center"
    }
]
```

### 3. Adicionar um Novo Evento

**Endpoint:** `/api/event/add`  
**Método:** `POST`  
**Descrição:** Adiciona um novo evento.

**Exemplo de Requisição:**
```http
POST /api/event/add
Content-Type: application/json

{
    "title": "Tech Conference 2023",
    "details": "A comprehensive conference covering all aspects of technology.",
    "slug": "tech-conference-2023",
    "maxAttendees": 500,
    "startDate": "2023-10-15T09:00:00",
    "endDate": "2023-10-17T17:00:00",
    "location": "Convention Center, Tech City"
}
```

**Exemplo de Resposta:**
```json
{
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "title": "Tech Conference 2023",
    "details": "A comprehensive conference covering all aspects of technology.",
    "slug": "tech-conference-2023",
    "maxAttendees": 500,
    "startDate": "2023-10-15T09:00:00",
    "endDate": "2023-10-17T17:00:00",
    "location": "Convention Center, Tech City"
}
```

### 4. Atualizar um Evento Existente

**Endpoint:** `/api/event/update`  
**Método:** `PUT`  
**Descrição:** Atualiza um evento existente.

**Exemplo de Requisição:**
```http
PUT /api/event/update
Content-Type: application/json

{
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "title": "Tech Conference 2023 Updated",
    "details": "An updated description of the comprehensive conference covering all aspects of technology.",
    "slug": "tech-conference-2023-updated",
    "maxAttendees": 600,
    "startDate": "2023-10-15T09:00:00",
    "endDate": "2023-10-17T17:00:00",
    "location": "New Convention Center, Tech City"
}
```

**Exemplo de Resposta:**
```json
"Evento atualizado com sucesso"
```

### 5. Excluir um Evento

**Endpoint:** `/api/event/delete/{id}`  
**Método:** `DELETE`  
**Descrição:** Exclui um evento pelo seu ID.

**Exemplo de Requisição:**
```http
DELETE /api/event/delete/3fa85f64-5717-4562-b3fc-2c963f66afa6
```

**Exemplo de Resposta:**
```json
"Evento excluído com sucesso"
```

## Notas

- Certifique-se de que os UUIDs usados nas requisições sejam válidos e existam no banco de dados, quando aplicável.
- O campo `slug` geralmente é gerado com base no título do evento, considere se você precisa atualizá-lo manualmente.
- Mensagens de erro e respostas de sucesso são retornadas em texto simples. Considere tratá-las adequadamente em sua aplicação cliente.
- Para o endpoint `PUT`, o campo `id` é obrigatório no corpo da requisição para identificar o evento a ser atualizado. Os outros campos são opcionais e serão atualizados somente se fornecidos.

## Modelos

### Evento

```json
{
    "id": "UUID",
    "title": "String",
    "details": "String",
    "slug": "String",
    "maxAttendees": "Integer",
    "startDate": "DateTime",
    "endDate": "DateTime",
    "location": "String"
}
```