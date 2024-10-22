# API de Conversão de Medidas

Este projeto é uma API REST desenvolvida com Spring Boot para converter valores de metros para outras unidades de medida (quilômetros, centímetros e milímetros) e armazenar os resultados em um banco de dados PostgreSQL. O banco de dados está configurado para rodar em um contêiner Docker.

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter os seguintes requisitos instalados:

- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started)

## Como executar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/meninn/meter-converter-api.git
cd meter-converter-api
```

### 2. Iniciar o banco de dados PostgreSQL com Docker

Execute o seguinte comando para criar e iniciar o contêiner do PostgreSQL:

```bash
docker run --name pg-database -e POSTGRES_USER=student -e POSTGRES_PASSWORD=student -e POSTGRES_DB=measurements_db -p 5432:5432 -d postgres
```

Isso criará um banco de dados chamado `measurements_db` com o usuário `student` e senha `student`, exposto na porta `5432`.

### 3. Configuração do banco de dados

Verifique se o arquivo `src/main/resources/application.properties` contém as seguintes configurações para conectar ao banco de dados:

```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/measurements_db
spring.datasource.username=student
spring.datasource.password=student
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuração do JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Executar a aplicação

Execute o comando abaixo para iniciar a aplicação Spring Boot:

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

### 5. Testar a API

Você pode usar `curl` ou ferramentas como Postman para testar a API. Exemplos de chamadas:

#### Converter metros para quilômetros:

```bash
curl "http://localhost:8080/convert/meter-to-kilometer?meters=1000"
```

#### Converter metros para centímetros:

```bash
curl "http://localhost:8080/convert/meter-to-centimeter?meters=1000"
```

#### Converter metros para milímetros:

```bash
curl "http://localhost:8080/convert/meter-to-millimeter?meters=1000"
```

Os valores convertidos serão armazenados no banco de dados.

### 6. Verificar dados no PostgreSQL

Você pode acessar o banco de dados PostgreSQL rodando no Docker com o seguinte comando:

```bash
docker exec -it pg-database psql -U student -d measurements_db
```

Depois, você pode verificar os dados armazenados executando a seguinte query SQL:

```sql
SELECT * FROM measurement;
```

## Estrutura do Projeto

```
meter-converter-api
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── converter
│   │   │               ├── Measurement.java
│   │   │               ├── MeasurementRepository.java
│   │   │               ├── MeterConverterController.java
│   │   │               ├── MeterConverterService.java
│   │   │               └── ConverterApplication.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── converter
│                       └── MeterConverterControllerTests.java
└── pom.xml
```

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação usada para desenvolver a API.
- **Spring Boot**: Framework para criar a API REST e facilitar a integração com o banco de dados.
- **PostgreSQL**: Banco de dados relacional onde as conversões serão armazenadas.
- **Docker**: Para rodar a instância do PostgreSQL.
- **Maven**: Gerenciador de dependências e automação do build.
