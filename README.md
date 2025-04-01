## Tecnologias Utilizadas

- Spring Boot
- Java 17
- GitHub

## Cronograma

| Atividade                              | Tempo   | Descrição                                                                                                                                                           |
| -------------------------------------- |---------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Configuração inicial                   | 1 hora  | Configuração do ambiente de desenvolvimento, incluindo dependências, tratamento global de exceções, e integração contínua com GitHub Actions.                       |
| Adição das entidades e relacionamentos | 3 hora  | Modelagem das entidades e modelagem do banco de dados.                                                                                                              |
| Adição dos controladores e services    | 4 horas | Adição de lógica na camada de serviço e adição da camada controller                                                                                                 |
| Gerar relatório em Planilha            | 5 horas | Tentando lembrar como usar o POI apache rsrsrs...                                                                                                                   |
| Gerar relatório em PDF                 | 5 horas | Problemas nas versões do jasper                                                                                                                                     |
| Refatoração e testes                   | 0 horas | Revisão e melhoria do código para garantir legibilidade e conformidade com boas práticas, além da execução de testes para validar as funcionalidades implementadas. |



## Pré-requisitos
- Java 17 ou superior
- GRADLE latest
- Docker (Para rodar o banco postgres:latest)

## Executando o Projeto
- crie o compose do postgres
- 1 - Procure o compose do postgres na raiz do projeto e execute:


   ```sh
   docker-compose up -d
   ```
- 2 - Adicione as configurações do banco em src->main->resources-> application.properties:

```sh
    Postgres
    spring.datasource.url=jdbc:postgresql://localhost:5432/mydatabase
    spring.datasource.username=myuser
    spring.datasource.password=secret
    spring.datasource.driverClassName=org.postgresql.Driver

    logging.level.org.springframework.web=DEBUG

    spring.jpa.show-sql=true
    spring.jpa.generate-ddl=true
   ```

- 3 - execute o projeto setando o run da sua IDE

- 4 - execute as migrations, lá já possui os inserts

- 5 - rode na porta 8080