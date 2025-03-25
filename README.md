9# Cadastro de Produtos
## Descrição:
Uma API simples desenvolvida em Java utilizando o framework Spring Boot. Esta aplicação permite gerenciar o cadastro de produtos, oferecendo funcionalidades como criação, leitura, atualização e exclusão (CRUD) de produtos em um banco de dados relacional e a utilização 
do Spring Security para gerenciamento de permissões e acessos.

## Tecnologias Utilizadas:

* Java
* Spring Boot 
* PostgresSQL 
* Docker
* PostMan

## Funcionalidades:
* Cadastro de Produtos: Adicione novos produtos com informações detalhadas.
* Listagem de Produtos: Visualize todos os produtos cadastrados.
* Atualização de Produtos: Edite as informações de produtos existentes.
* Remoção de Produtos: Exclua produtos do cadastro.

## EndPoints:

### Autenticação
```
POST /auth/login/ - Realizar Login
POST /auth/register/ - Realizar Registro de Usuário
```
### Usuário
```
POST /products/add/ - Adicionar Produtos
GET /products/ - Listar Produtos
```
## Como Excutar:

1. Clone o repositório
2. Buildar o docker-compose
```
docker-compose build
```
3. Rodar o projeto
```
docker-compose  up -d
```

## Requisitos:

* Docker
