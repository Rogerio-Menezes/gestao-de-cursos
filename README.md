Gestao de Cursos
Este projeto é uma aplicação Spring Boot para a gestão de cursos. Ele permite criar, atualizar, consultar e deletar cursos, além de ativar ou desativar cursos.

Endpoints da API
A API é baseada no padrão REST e oferece os seguintes endpoints:

1. Criar um Novo Curso
URL: /course/
Método: POST
Descrição: Cria um novo curso.
Request Body:
json
{
  "name": "Nome do Curso",
  "category": "Categoria do Curso",
  "shift": "Turno do Curso"
}
Respostas:
201 Created: Curso criado com sucesso.
500 Internal Server Error: Erro ao criar o curso.
3. Obter Todos os Cursos
URL: /course/
Método: GET
Descrição: Retorna a lista de todos os cursos.
Respostas:
200 OK: Lista de cursos.
500 Internal Server Error: Erro ao obter a lista de cursos.
4. Atualizar um Curso Existente
URL: /course/{id}
Método: PUT
Descrição: Atualiza as informações de um curso existente.
Request Body:
json
Copiar código
{
  "name": "Nome Atualizado do Curso",
  "category": "Categoria Atualizada do Curso"
  "shift":  "Turno Atualizado do Curso",
  "active:  "Ativo ou Não Atualizado do Curso
}
Respostas:
200 OK: Curso atualizado com sucesso.
404 Not Found: Curso não encontrado.
500 Internal Server Error: Erro ao atualizar o curso.
6. Atualizar o Status Ativo de um Curso
URL: /course/{id}/active
Método: PATCH
Descrição: Atualiza o status ativo de um curso.
Query Parameters:
active: true para ativar o curso, false para desativar.
Respostas:
200 OK: Status do curso atualizado com sucesso.
404 Not Found: Curso não encontrado.
500 Internal Server Error: Erro ao atualizar o status do curso.
7. Excluir um Curso
URL: /course/{id}
Método: DELETE
Descrição: Exclui um curso existente.
Respostas:
200 OK: Curso excluído com sucesso.
404 Not Found: Curso não encontrado.
500 Internal Server Error: Erro ao excluir o curso.


Tecnologias Usadas
Spring Boot: Framework para desenvolvimento de aplicações Java.
PostgreSQL: Banco de dados relacional utilizado para armazenamento dos dados.
Flyway: Ferramenta de migração de banco de dados para gerenciamento de alterações no esquema do banco de dados.
JUnit: Framework para testes unitários
