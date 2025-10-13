# Sistema de Portaria Light

O Sistema de Portaria Light é uma aplicação mobile desenvolvida para automatizar o controle de encomendas em condomínios residenciais, proporcionando mais agilidade, segurança e organização no processo de recebimento e retirada de pacotes. A proposta do sistema é modernizar o gerenciamento de entregas, substituindo processos manuais por uma solução digital integrada a um banco de dados relacional.

## Integrantes

- **RM560179** Lucas da Ressurreição Barbosa — Java Advanced / IOT
- **RM560694** Fabrício José da Silva — .Net / Dados
- **RM559210** Ranaldo José da Silva — Mobile / QA / DevOps

## Modelo DER

![Modelo DER](./docs/modelo_der.png)

## Funcionalidades

- Cadastro, atualização, listagem e remoção de moradores
- Gerenciamento de portarias e porteiros
- Controle de encomendas recebidas e retiradas
- Integração com banco de dados relacional
- API RESTful para todas as entidades principais

## Estrutura do Projeto

- `entity`: Entidades JPA (Morador, Portaria, Encomenda, Retirada)
- `repository`: Repositórios Spring Data JPA
- `service`: Serviços de negócio
- `controller`: Controllers REST
- `resources/application.properties`: Configurações de banco de dados e JPA

## Requisitos

- Java 21
- Gradle
- Oracle Database (ou H2 para testes)
- Variáveis de ambiente: `DB_URL`, `DB_USER`, `DB_PASS`

## Como executar

1. Configure as variáveis de ambiente no arquivo `.env` ou diretamente no sistema.
2. Execute o comando abaixo para iniciar o projeto:

```sh
./gradlew bootRun
```

3. Acesse a API em `http://localhost:8080`.

## Endpoints principais

- `/moradores` — Gerenciamento de moradores
- `/portarias` — Gerenciamento de portarias
- `/encomendas` — Gerenciamento de encomendas
- `/retiradas` — Gerenciamento de retiradas

## Testes

Para rodar os testes automatizados:

```sh
./gradlew test
```

## Vídeo de Apresentação

[Assista aqui](https://youtu.be/DnbmD0YmciU)

## Licença

Projeto acadêmico FIAP. Uso livre para fins educacionais.