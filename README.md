# Gestão de Cursos

Este projeto é uma aplicação **Spring Boot** para a gestão de cursos. Ele permite criar, atualizar, consultar e excluir cursos, altera a situação cursos. O sistema possui autenticação baseada em **Spring Security** com **JWT (JSON Web Token)** e controle de acesso com papéis (roles). Existe uma diferenciação entre os papéis de **Admin** e **User**, sendo que o **Admin** tem permissões para gerenciar cursos, enquanto o **User** tem permissões limitadas a apenas consultar cursos.

## Funcionalidades da API

A API é baseada no padrão **REST** e oferece os seguintes endpoints:

### 1. Criar um Novo Curso
- **URL:** `/course/`
- **Método:** `POST`
- **Descrição:** Cria um novo curso (apenas usuários com role de **Admin** podem acessar).
- **Request Body:**
  ```json
  {
    "name": "Nome do Curso",
    "category": "Categoria do Curso",
    "shift": "Turno do Curso"
  }
Respostas:
201 Created: Curso criado com sucesso.

500 Internal Server Error: Erro ao criar o curso.
### 2. Obter Todos os Cursos
- **URL:** /course/
- **Método:** GET
- **Descrição:** Retorna a lista de todos os cursos (acessível por usuários com Admin e User).
Respostas:
200 OK: Lista de cursos.
  
500 Internal Server Error: Erro ao obter a lista de cursos.
### 3. Atualizar um Curso Existente
- **URL:** /course/{id}
- **Método:** PUT
- **Descrição:** Atualiza as informações de um curso existente (apenas usuários com role de Admin podem acessar).
- **Request Body:**
  ```json
  {
    "name": "Nome Atualizado do Curso",
    "category": "Categoria Atualizada do Curso",
    "shift": "Turno Atualizado do Curso",
    "situation": "Situação do Curso"
  }
Respostas:
200 OK: Curso atualizado com sucesso.

404 Not Found: Curso não encontrado.

500 Internal Server Error: Erro ao atualizar o curso.
### 4. Atualizar a Situação de um Curso
- **URL:** /course/{id}/status
- **Método:** PATCH
- **Descrição:** Atualiza a Situação de um curso (acessível por Admin).
- **Query Parameters:** status?situation=ACTIVE   valores aceitos: ACTIVE, PENDING, CLOSED, CANCELED, COMPLETED..
Respostas:
200 OK: Status do curso atualizado com sucesso.
  
404 Not Found: Curso não encontrado.

500 Internal Server Error: Erro ao atualizar o status do curso.
### 5. Excluir um Curso
- **URL:** /course/{id}
- **Método:** DELETE
- **Descrição:** Exclui um curso existente (apenas usuários com role de Admin podem acessar).
Respostas:
200 OK: Curso excluído com sucesso.
  
404 Not Found: Curso não encontrado.

500 Internal Server Error: Erro ao excluir o curso.
### 6. Obter Curso 
- **URL:** /course/{id}
- **Método:** GET
- **Descrição:** Retorna o curso (acessível por usuários com Admin e User).
Respostas:
200 OK: Dados do curso.
  
500 Internal Server Error: Erro ao obter a lista de cursos.

## Autenticação e Autorização
Este projeto utiliza Spring Security para autenticação e autorização de usuários. O sistema é baseado em JSON Web Tokens (JWT), onde o token de autenticação é gerado ao fazer login e deve ser enviado em todas as requisições subsequentes.

#### Roles de Usuário:
- **Admin:** Pode criar, atualizar, excluir e ativar/desativar cursos. Também pode visualizar todos os cursos.
- **User:** Pode visualizar os cursos, mas não tem permissão para criar, editar ou excluir cursos.
#### Fluxo de Autenticação:
- **Login:** O usuário faz login enviando suas credenciais (email e senha).
  ```json
  {
	  "email": "admin",
 	  "password": "admin123"
  }
- **Geração de Token:** Se as credenciais forem válidas, a API retorna um JWT que deve ser usado nas próximas requisições.
- **Validação do Token:** O token é validado em cada requisição subsequente. Dependendo do role (Admin ou User), o acesso é permitido ou negado.
- **Controle de Acesso:** Dependendo do role, as permissões são verificadas:
- **Admin:** pode acessar todas as rotas.
- **User:** só pode acessar rotas que envolvem visualização de cursos.
#### Exemplo de Cabeçalho de Autorização:
- **Authorization:** Bearer <seu_token_jwt_aqui>
### Tecnologias Usadas
- **Spring Boot:** Framework para desenvolvimento de aplicações Java.
- **Spring Security:** Biblioteca para autenticação e autorização de usuários.
- **PostgreSQL:** Banco de dados relacional utilizado para armazenamento dos dados.
- **Flyway:** Ferramenta de migração de banco de dados para gerenciamento de alterações no esquema do banco de dados.
- **JUnit:** Framework para testes unitários.
- **JWT (JSON Web Token):** Para a autenticação baseada em token.
##### Projeto feito para uma aula no curso de Java da Rocketseat.
