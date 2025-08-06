# **Sistema de Gestão para Igrejas - Portal NT Control**

Aplicação web para o gerenciamento integrado de membros, células, lideres e frequência nos cultos, desenvolvida par a igreja.

## **Visão Geral**

Este projeto é uma plataforma de gestão robusta, criada para centralizar e simplificar a administração de informações vitais da igreja. O sistema é dividido em módulos para gerenciar Células, Lideres, Membros e a Frequência nos cultos, oferecendo uma solução completa e organizada para acompanhar o crescimento e o engajamento da comunidade.

### **Módulos do Sistema**

* **Gestão de Células:**
    * Cadastro, consulta, atualização e exclusão de células (pequenos grupos).
    * Detalhes como nome da célula, líder, membros.

* **Gestão de Membros:**
    * Cadastro completo de membros com informações pessoais (nome, e-mail, telefone, data de nascimento).
    * Operações de CRUD (Create, Read, Update, Delete) para os membros.

* **Controle de Frequência:**
    * Registro detalhado da frequência nos cultos, segmentado por homens, mulheres, crianças e visitantes.
    * Consulta de registros de frequência por data específica.
    * Atualização e remoção de registros de frequência.

## **Instalação**

Siga as instruções abaixo para configurar e executar o projeto em seu ambiente local.

### **Pré-requisitos**

* **Java Development Kit (JDK) 21**
* **Maven**
* **Docker e Docker Compose**
* **Git**

### **Passos de Instalação**

1.  **Clone o repositório:**
    ```sh
    git clone [https://github.com/seu-usuario/portalnt-control.git](https://github.com/seu-usuario/portalnt-control.git)
    cd portalnt-control
    ```

2.  **Configure as variáveis de ambiente:**
    Crie um arquivo `.env` na raiz do projeto com base nas configurações dos arquivos `docker-compose.yml` e `application.yml`.
    ```env
    DB_USER=seu_usuario
    DB_PASSWORD=sua_senha
    DB_NAME=gestao_igreja_db
    DB_URL=jdbc:postgresql://localhost:5432/gestao_igreja_db
    PGADMIN_EMAIL=seu_email@exemplo.com
    PGADMIN_PASSWORD=sua_senha_pgadmin
    ```

3.  **Inicie o banco de dados com Docker Compose:**
    ```sh
    docker-compose up -d
    ```

4.  **Execute a aplicação:**
    ```sh
    ./mvnw spring-boot:run
    ```
    
## **Tecnologias Utilizadas**

* **Backend:** Java 21
* **Framework:** Spring Boot
* **Banco de Dados:** PostgreSQL
* **Gerenciamento de Dependências:** Maven
* **Contêiner:** Docker
