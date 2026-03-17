# Fintrack 💰

API RESTful de gestão financeira pessoal desenvolvida com Java e Spring Boot. Permite que usuários controlem suas receitas, despesas e metas mensais de forma segura e organizada.

## Funcionalidades

- Cadastro e autenticação de usuários com JWT
- Lançamento de receitas e despesas por categoria
- Definição de meta de gasto mensal
- Consulta de transações por usuário autenticado
- CRUD completo de usuários, transações e metas
- Tratamento global de exceções com respostas padronizadas
- Documentação interativa com Swagger UI

## Tecnologias

- **Java 21**
- **Spring Boot 4**
- **Spring Security** com autenticação stateless via JWT
- **Spring Data JPA** com Hibernate
- **PostgreSQL** como banco de dados relacional
- **Docker** e **Docker Compose** para containerização
- **MapStruct** para mapeamento de DTOs
- **Lombok** para redução de boilerplate
- **Springdoc OpenAPI** para documentação

## Arquitetura

O projeto segue uma arquitetura em camadas:

```
controller  →  service  →  repository  →  banco de dados
     ↕              ↕
    dto           model
```

- **Controller** — recebe requisições HTTP e delega para o service
- **Service** — contém a lógica de negócio e validações
- **Repository** — acesso ao banco via Spring Data JPA
- **DTO** — objetos de transferência de dados (request/response)
- **Mapper** — conversão entre entidades e DTOs via MapStruct

## Segurança

- Autenticação via JWT (JSON Web Token)
- Senhas criptografadas com BCrypt
- Filtro de segurança customizado validando token a cada requisição
- Endpoints protegidos — cada usuário acessa apenas seus próprios dados

## Como rodar localmente

### Pré-requisitos

- Java 21
- Docker e Docker Compose
- Maven

### Passos

1. Clone o repositório

```bash
git clone https://github.com/Arthur32p/Fintrack.git
cd Fintrack
```

2. Suba o banco de dados com Docker

```bash
docker-compose up -d
```

3. Configure o arquivo `application-local.yml` na raiz de `src/main/resources`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/fintrack
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

api:
  security:
    token:
      secret: sua-chave-secreta
```

4. Rode a aplicação

```bash
./mvnw spring-boot:run
```

5. Acesse a documentação em `http://localhost:8080/swagger-ui.html`

## Endpoints principais

### Autenticação
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/user` | Cadastro de usuário |
| POST | `/auth/login` | Login e geração de token |

### Transações
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/transaction` | Criar transação |
| GET | `/transaction` | Listar transações do usuário |
| GET | `/transaction/{id}` | Buscar por ID |
| PUT | `/transaction/{id}` | Atualizar transação |
| DELETE | `/transaction/{id}` | Deletar transação |

### Metas
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/goal` | Criar meta mensal |
| GET | `/goal` | Listar metas do usuário |
| GET | `/goal/{id}` | Buscar por ID |
| PUT | `/goal/{id}` | Atualizar meta |
| DELETE | `/goal/{id}` | Deletar meta |

## Categorias disponíveis

`FOOD`, `TRANSPORT`, `HEALTH`, `EDUCATION`, `ENTERTAINMENT`, `HOUSING`, `OTHER`
