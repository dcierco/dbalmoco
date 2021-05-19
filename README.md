# DBalmoco
<h4 align="center"> 
	🚧  Em construção...  🚧
</h4>
<p align="center"> Projeto de REST Api para desenvolver um sistema de votações para restaurantes onde os funcionários irão almoçar</p>
<p align="center">
 <a href="#objetivos">Objetivos</a> •
 <a href="#pré-requisitos">Pré requisitos e Execução</a> • 
 <a href="#tecnologias">Tecnologias Usadas</a> •
 <a href="#descrição-da-implementação">Descrição da Implementação</a> •
 <a href="#implementação">Melhorias Propostas e Proximas Versões</a>
</p>

## Objetivos

Essa aplicação tem como objetivo apenas demonstrar a proficiencia do autor com a linguagem e as tecnologias usadas na aplicação, o desafio proposto pode ser lido no documento .docx na pasta raiz do projeto. 

## Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
Git, Java 8 (Ou Superior) e PostgreSQL.

### 🎲 Rodando a API

```bash
# Clone este repositório
$ git clone <https://github.com/dcierco/dbalmoco>

# Acesse a pasta do projeto no terminal/cmd
$ cd dbalmoco

# Compile o projeto
mvn -DskipTests=true package
(a ação de pular os testes se dá necessaria por ajustes ainda requeridos na configuração de testes de api)

# Execute a aplicação
$ ./mvnw spring-boot:run

# Para ver o swagger da api acesse: <http://localhost:8080/swagger-ui/>
```



## 🛠 Tecnologias Usadas

As seguintes tecnologias foram utilizadas no projeto:

- [JavaSE-1.8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
- [Maven](https://maven.apache.org/)
- [Spring](https://spring.io/)
- [PostgreSQL](https://www.postgresql.org/)
- [TestNG](https://testng.org/doc/)
- [Mockito](https://site.mockito.org/)
- [Lombok](https://projectlombok.org/)
- [Swagger](https://swagger.io/)
- [RestAssured](https://rest-assured.io/)
- [Extent Reports](https://www.extentreports.com/)


## Descrição da Implementação

Foram criadas 3 entidades que são as essenciais para as régras de negócio descritas, elas são: Voto, Restaurante e Funcionário. 
A classe Restaurante, descreve as propriedades de um restaurante que pode ser votado por um funcionário, que tem sua propria entidade com esse nome e descreve também suas características. Já a classe voto representa o relacionamento entre essas duas classes seguindo as regras descritas.
Todas as entidades seguem os padrões de controllers, services e classes de repositório, tendo bem definidos seus respectivos CRUD.
O versionamento do banco de dados é feito com o FlyWay e existe uma versão incial de uma arquitetura do Spring Security que não está ativa nem concluída, que trata a autenticação e autorização com JWT.

Todas as Estórias descritas nos requisitos do projeto são implementadas de forma satisfatória, e podem ser vistas utilizando a arquitetura do swagger.

Dentro da pasta de testes existem testes de API (Que deveriam e irão estar em um projeto separado em versões futuras), e testes unitários (alguns ainda não concluídos na versão atual). Os testes de API são implementados usando RestAssured e TestNG, além de terem um local separado onde ficam os reportes que são gerados pelo extent.


## Melhorias Propostas e Proximas Versões

- Conclusão dos testes Unitários de um Serviço, que ficou faltando.

- Os testes de API devem ir para um projeto separado

- Implementação da Segurança com JWT

- Refatoração do código para remover métodos não muito utilizados, e que nem deveriam existir.

- Implementação do cliente que consome a API.

- Existe um processo de configuração da integração continua com o circleci, mas não foi configurado o banco de dados e algumas dependencias, é algo que também precisa ser consertado.

Algumas melhorias dessas devem ser implementadas nos proximos dias e, com isso, removidas desse readme.

