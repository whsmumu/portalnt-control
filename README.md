# **Monitoramento de Frequência**

Desenvolvimento web para monitoramente e gerenciamento de frequência nos cultos da igreja

## **Visão Geral**

Este projeto é uma aplicação web desenvolvida para registrar e gerenciar a frequência de membros e visitantes em cultos da igreja. Ele permite que os usuários insiram, visualizem, atualizem e excluam registros de frequência, fornecendo uma maneira organizada de rastrear a participação ao longo do tempo.

### **Principais Funcionalidades**

* **Registro de Frequência:** Adicione novos registros de frequência com detalhes sobre o número de membros e visitantes.
* **Visualização de Dados:** Consulte os registros de frequência por data, mês ou ano.
* **Gerenciamento de Registros:** Atualize ou exclua registros de frequência existentes.
* **Validação de Dados:** Garante a integridade dos dados, prevenindo registros duplicados para a mesma data.
* **API RESTful:** Oferece endpoints para interagir com o sistema de forma programática.

## **Instalação**

Siga as instruções abaixo para configurar e executar o projeto em seu ambiente local.

### **Pré-requisitos**

* **Java Development Kit (JDK) 21:** Certifique-se de ter o JDK 21 instalado.
* **Maven:** O projeto utiliza o Maven para gerenciamento de dependências.
* **Docker e Docker Compose:** Necessário para executar o banco de dados PostgreSQL em um contêiner.
* **Git:** Para clonar o repositório.

### **Passos de Instalação**

1.  **Clone o repositório:**
    ```sh
    git clone https://github.com/whsmumu/frequency-monitoring.git
    cd frequency-monitoring
    ```

2.  **Configure as variáveis de ambiente:**
    Crie um arquivo `.env` na raiz do projeto com base no arquivo `docker-compose.yml` e `application.yml`.
    ```env
    DB_USER=seu_usuario
    DB_PASSWORD=sua_senha
    DB_NAME=seu_banco_de_dados
    DB_URL=jdbc:postgresql://localhost:5432/seu_banco_de_dados
    PGADMIN_EMAIL=seu_email@exemplo.com
    PGADMIN_PASSWORD=sua_senha_pgadmin
    ```

3.  **Inicie o banco de dados com Docker Compose:**
    ```sh
    docker-compose up -d
    ```

4.  **Execute a aplicação:**
    Use o Maven Wrapper para compilar e executar a aplicação Spring Boot.
    ```sh
    ./mvnw spring-boot:run
    ```

## **Endpoints da API**

Após a instalação, a aplicação estará disponível em `http://localhost:8080`. Você pode interagir com a API.

### **Criar um novo registro de frequência**

* **Método:** `POST`
* **URL:** `http://localhost:8080/frequencias`
* **Headers:**
    * `Content-Type`: `application/json`
* **Body (raw, JSON):**
    ```json
    {
        "data": "31/07/2025",
        "quantidadeMembrosHomem": 50,
        "quantidadeMembrosMulheres": 60,
        "quantidadeVisitantesHomem": 10,
        "quantidadeVisitantesMulher": 15,
        "quantidadeKids": 20,
        "quantidadeBaby": 5
    }
    ```

### **Obter todos os registros**

* **Método:** `GET`
* **URL:** `http://localhost:8080/frequencias`

### **Obter registros por data**

* **Método:** `GET`
* **URL:** `http://localhost:8080/frequencias`
* **Params:**
    * Key: `data`, Value: `31/07/2025`

### **Atualizar um registro existente**

* **Método:** `PUT`
* **URL:** `http://localhost:8080/frequencias/{id}` (substitua `{id}` pelo ID do registro)
* **Headers:**
    * `Content-Type`: `application/json`
* **Body (raw, JSON):**
    ```json
    {
        "data": "31/07/2025",
        "quantidadeMembrosHomem": 55,
        "quantidadeMembrosMulheres": 65,
        "quantidadeVisitantesHomem": 12,
        "quantidadeVisitantesMulher": 18,
        "quantidadeKids": 22,
        "quantidadeBaby": 6
    }
    ```

### **Excluir um registro**

* **Método:** `DELETE`
* **URL:** `http://localhost:8080/frequencias/{id}` (substitua `{id}` pelo ID do registro)

## **Tecnologias Utilizadas**

* **Backend:** Java 21
* **Framework:** Spring Boot
* **Banco de Dados:** PostgreSQL
* **Gerenciamento de Dependências:** Maven
* **Contêiner:** Docker

### **Dependências do Spring Boot**

* `spring-boot-starter-data-jpa`: Para persistência de dados com JPA.
* `spring-boot-starter-validation`: Para validação de dados nos DTOs.
* `spring-boot-starter-web`: Para criar aplicações web e APIs RESTful.
* `spring-boot-devtools`: Para facilitar o desenvolvimento (ex: live reload).
* `postgresql`: Driver JDBC para o PostgreSQL.
* `lombok`: Para reduzir código boilerplate (getters, setters, etc.).

## **Estrutura de Pastas**

```
frequency-monitoring/
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── whsmumu/github/frequencyMonitoring/
│   │   │       ├── controller/
│   │   │       │   ├── common/
│   │   │       │   │   └── GlobalExceptionHandler.java
│   │   │       │   ├── dto/
│   │   │       │   │   ├── ErrorField.java
│   │   │       │   │   ├── ErrorResponse.java
│   │   │       │   │   └── FrequencyDTO.java
│   │   │       │   └── FrequencyController.java
│   │   │       ├── exceptions/
│   │   │       │   ├── RecordDuplicateException.java
│   │   │       │   └── RecordNotFoundException.java
│   │   │       ├── model/
│   │   │       │   └── Frequency.java
│   │   │       ├── repository/
│   │   │       │   └── FrequencyRepository.java
│   │   │       ├── service/
│   │   │       │   └── FrequencyService.java
│   │   │       ├── validator/
│   │   │       │   └── FrequencyValidator.java
│   │   │       └── FrequencyMonitoringApplication.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/
│           └── whsmumu/github/frequencyMonitoring/
│               └── FrequencyMonitoringApplicationTests.java
├── .gitattributes
├── .gitignore
├── docker-compose.yml
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```
