# Sistema de Gestão de Portaria

## Descrição
Sistema para gerenciamento de portaria de condomínio, focado no controle de entrega e retirada de encomendas. A aplicação permite o registro, acompanhamento e controle de encomendas recebidas na portaria do condomínio.

## Integrantes e Responsabilidades

- **Lucas da Ressurreição Barbosa (RM560179)**
  - Desenvolvimento Backend (Java/Spring)
  - Implementação da API REST
  - Documentação técnica
  
- **Fabrício José da Silva (RM560694)**
  - Modelagem de dados
  - Implementação das entidades JPA
  - Testes de integração

- **Ranaldo José da Silva (RM559210)**
  - DevOps e CI/CD
  - Testes de qualidade
  - Infraestrutura

## Cronograma de Desenvolvimento

[Link para o cronograma detalhado no Trello](https://trello.com/b/xyz123)

## Arquitetura

### Diagrama de Classes
![Diagrama de Classes](docs/images/class-diagram.png)

### Diagrama ER
![Diagrama ER](docs/images/er-diagram.png)

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.2
- JPA/Hibernate
- Oracle Database
- Swagger/OpenAPI
- JUnit 5
- Gradle

## Como Executar

### Pré-requisitos
- JDK 21
- Gradle 8.x
- Oracle Database 19c
- Git

### Configuração
1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/portaria.git
cd portaria
```

2. Configure as variáveis de ambiente:
```properties
DB_URL=jdbc:oracle:thin:@//localhost:1521/xe
DB_USER=seu_usuario
DB_PASS=sua_senha
```

3. Execute a aplicação:
```bash
./gradlew bootRun
```

## Documentação da API

A documentação completa da API está disponível através do Swagger UI em:
```
http://localhost:8080/swagger-ui.html
```

### Endpoints Principais

#### Apartamentos
- `GET /api/v1/apartamentos` - Lista todos apartamentos
- `POST /api/v1/apartamentos` - Cadastra novo apartamento
- `GET /api/v1/apartamentos/{id}` - Obtém apartamento específico
- `PUT /api/v1/apartamentos/{id}` - Atualiza apartamento
- `DELETE /api/v1/apartamentos/{id}` - Remove apartamento

[Documentação completa dos endpoints](docs/endpoints.md)

## Testes

Execute os testes automatizados com:
```bash
./gradlew test
```

A cobertura de testes pode ser verificada em:
```bash
./gradlew jacocoTestReport
```

## Links Importantes

- [Vídeo de Apresentação](https://youtube.com/xyz123)
- [Documentação Técnica](docs/technical.md)
- [Coleção Postman](docs/postman/portaria.json)

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.