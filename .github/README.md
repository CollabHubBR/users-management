# CollabHubBR - Users Management

![GitHub License](https://img.shields.io/github/license/CollabHubBR/users-management?labelColor=101010)

<!-- ![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/CollabHubBR/users-management/testing.yml?style=flat&labelColor=101010) -->

Este repositório contém o código-fonte do **Microsserviço de Usuários** do **CollabHubBR**, a plataforma brasileira de coordenação e organização de projetos de código-aberto. Desenvolvido em **Java** com o framework **Spring**, este serviço é o pilar para o gerenciamento de todas as entidades relacionadas a usuários na plataforma.

As principais responsabilidades deste microsserviço incluem a gestão de **usuários**, **perfis**, **permissões** e **grupos**. Ele orquestra o sistema de autorização, permitindo que usuários criadores de projetos convidem outros usuários para se tornarem administradores de projetos, definindo permissões específicas. Isso pode ser concedido como `superuser` (todas as permissões) ou por **roles detalhadas**, como `KanbanRole`, `PollRole`, `RoadmapRole`, entre outras. A persistência dos dados é garantida pelo **PostgreSQL**, oferecendo robustez e escalabilidade.

## Stack

![Java](https://img.shields.io/badge/Java-ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

![Spring](https://img.shields.io/badge/spring-6DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

<!-- ![Nginx](https://img.shields.io/badge/nginx-009639.svg?style=for-the-badge&logo=nginx&logoColor=white) -->
<!-- ![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black) -->

![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

![JUnit5](https://img.shields.io/badge/JUnit5-dc524a?style=for-the-badge&logo=JUnit5&logoColor=ffffff)

![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)

<!-- ![Render](https://img.shields.io/badge/Render-46E3B7?style=for-the-badge&logo=render&logoColor=000&color=fff) -->

![GitHub](https://img.shields.io/badge/GitHub-fff?style=for-the-badge&logo=github&logoColor=181717)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-2088ff?style=for-the-badge&logo=github-actions&logoColor=fff)

## Arquitetura

A arquitetura do Microsserviço de Usuários do CollabHubBR segue os princípios de microsserviços, focando na **separação de responsabilidades**, **escalabilidade** e **manutenibilidade**. Adotamos uma estrutura de pacotes modular para organizar nossas entidades, controladores, serviços, repositórios e configurações.

```mermaid
flowchart TD

    A[Usuário] --> B{API Gateway}
    B --> C[Microsserviço de Usuários]
    C --> D(Controller)
    D --> E(Service)
    E --> F(Repository)
    F --> G[PostgreSQL Database]
    E --> H(Security & Auth)
    H --> I[JWT Authentication]
    H --> J(Permissions & Roles Management)
    J --> K[Invitations Logic]
```

### Estrutura de Pastas

Abaixo, descrevemos a organização principal dos pacotes do projeto:

- `src/main/java/br/com/collabhubbr/users/`: Pacote raiz do código-fonte.
  - `config/`: Classes de configuração do Spring (segurança, banco de dados, etc.).
  - `controller/`: Endpoints REST para interações com o serviço (gerenciamento de usuários, perfis, convites).
  - `model/`: Classes de entidade que mapeiam as tabelas do banco de dados (User, Profile, Permission, Role, Group, Invitation).
  - `repository/`: Interfaces JPA para acesso e manipulação de dados no PostgreSQL.
  - `service/`: Lógica de negócio, orquestração e validação (criação de usuários, atribuição de roles, gerenciamento de convites).
  - `security/`: Implementações de segurança (autenticação, autorização, JWT).
  - `util/`: Classes utilitárias e helpers.
  - `CollabhubbrUsersApplication.java`: Classe principal da aplicação Spring Boot.
- `src/main/resources/`:
  - `application.properties` ou `application.yml`: Arquivos de configuração do Spring (conexão com o banco de dados, portas, etc.).
- `src/test/java/br/com/collabhubbr/users/`: Pacotes para testes unitários e de integração.

### Instalação de Dependências

```bash
mvn clean install
```

### Configuração do Banco de Dados

Crie um banco de dados PostgreSQL e configure as credenciais no `src/main/resources/application.properties` (ou `application.yml`):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/collabhub_users_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Servidor Local

```bash
mvn spring-boot:run
```

### Execução de Testes

```bash
mvn test
```

## To-Do List

Confira a [To-Do List aqui](https://github.com/CollabHubBR/users-management/blob/main/.github/TODO.md)

## Contrib

Antes de contribuir ativamente com o projeto é **fortemente recomendada** a leitura dos documentos abaixo:

- [Código de Conduta](https://github.com/CollabHubBR/.github/blob/main/CODE_OF_CONDUCT.md)
- [Contribuindo](https://github.com/CollabHubBR/.github/blob/main/CONTRIBUTING.md)
- [Segurança](https://github.com/CollabHubBR/.github/blob/main/SECURITY.md)
- [Suporte](https://github.com/CollabHubBR/.github/blob/main/SUPPORT.md)

## Licença

This project is under [MIT - Massachusetts Institute of Technology](https://choosealicense.com/licenses/mit/). A short and simple permissive license with conditions only requiring preservation of copyright and license notices. Licensed works, modifications, and larger works may be distributed under different terms and without source code.
