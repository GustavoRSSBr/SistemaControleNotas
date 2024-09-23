
# Sistema de Controle de Notas

## Descrição

O **Sistema de Controle de Notas** é uma API desenvolvida em Java para gerenciar avaliações, notas e entregas entre professores e alunos. Professores podem criar avaliações, lançar notas e verificar as entregas feitas pelos alunos, enquanto os alunos podem consultar suas notas e enviar entregas para as avaliações. O sistema utiliza autenticação JWT para proteger as rotas da API e segue a **Arquitetura Hexagonal** para garantir uma separação clara de responsabilidades.

## Estrutura do Projeto

O projeto segue a arquitetura hexagonal (ports and adapters) e está organizado da seguinte forma:

```plaintext
java/
└── br/com/gustavorssbr/Sistema/de/Controle/de/Notas
    ├── adapter
    │   ├── input      # Controladores REST para receber as requisições além de suas intefaces documentadas e os DTOs relacionados.
    │   └── output     # Define a comunicação com o banco de dados e outros serviços externos.
    ├── config
    │   ├── dto        # Definições de Data Transfer Objects (DTOs) gerais da aplicação.
    │   ├── exception  # Exceções customizadas para o sistema e o handler da aplicação.
    │   └── segurança  # Configurações de segurança e autenticação JWT.
    ├── domain
    │   ├── command    # Casos de uso e lógica de negócio do sistema.
    │   ├── enums      # Enumerações para estados e constantes usadas no domínio.
    │   └── exceptions # Exceções específicas do domínio.
    ├── port
    │   ├── input      # Interfaces que definem as ações do sistema (ex: criar avaliação, lançar nota).
    │   └── output     # Interfaces para comunicação externa (ex: repositórios, APIs externas).
    ├── utils          # Utilitários usados pelo sistema.
resources/
├── application.yaml       # Arquivo de configuração da aplicação (Spring Boot).
└── rotas.json             # Arquivo que contém as definições de rotas da aplicação e suas ROLES.
```

### Configuração do **application.yaml**

Este é o arquivo de configuração `application.yaml`, que define as propriedades da aplicação, incluindo banco de dados, documentação da API via Swagger e pool de conexões do banco de dados.

```yaml
spring:
  application:
    name: Sistema de Controle de Notas

  springdoc:
    api-docs:
      path: /v3/api-docs
    swagger-ui:
      path: /swagger-ui.html

  datasource:
    driverClassName: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/{sua database}
    username: {seu usuario}
    password: {sua senha}

  hikari:
    maximumPoolSize: 3
    minimumIdle: 2
    idleTimeout: 20000
    poolName: SistemaDeControleDeNotaspool
```

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Framework para construção da API REST.
- **PostgreSQL**: Banco de dados relacional utilizado para persistência.
- **Swagger**: Utilizado para documentação da API e testes via interface gráfica.
- **JWT (JSON Web Token)**: Para autenticação e autorização.
- **Arquitetura Hexagonal (Ports and Adapters)**: Organização do código em camadas desacopladas, permitindo flexibilidade e melhor manutenção.

## Como Executar o Projeto

### Pré-requisitos:

1. **Java 17** ou superior.
2. **PostgreSQL** instalado e rodando.
3. **Maven** para gerenciar dependências.

### Passos:

1. **Clone o Repositório**:

   ```bash
   git clone https://github.com/GustavoRSSBr/SistemaControleNotas.git
   ```

2. **Configure o Banco de Dados**:
   - Modifique o arquivo `application.yaml` com as credenciais do seu PostgreSQL.
   - O banco de dados PostgreSQL utilizado no projeto pode ser baixado a partir do seguinte link:
   - [Download do Banco de Dados](#)  
   

3. **Execute o Projeto**:

   Utilize sua IDE favorita (IntelliJ IDEA, Eclipse) ou o terminal para executar o projeto.

   ```bash
   ./mvnw spring-boot:run
   ```

4. **Acesse o Swagger**:

   A documentação da API estará disponível em:

   ```
   http://localhost:8080/swagger-ui.html
   ```

## Autor

Desenvolvido por [GustavoRSSBr](https://github.com/GustavoRSSBr).
