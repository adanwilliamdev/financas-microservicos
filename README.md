<div align="center">

# 💰 Finanças Microservices

### Arquitetura de microserviços para um sistema de gestão financeira pessoal

Sistema desenvolvido utilizando **Spring Boot**, **Spring Cloud**, **Netflix Eureka** e **Spring Cloud Gateway**, seguindo uma arquitetura moderna baseada em microserviços.

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-2023-6DB33F?style=for-the-badge)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-336791?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Authentication-000000?style=for-the-badge&logo=jsonwebtokens)

</div>

---

# 📖 Sobre o Projeto

Este projeto implementa uma arquitetura baseada em **microserviços** para gerenciamento financeiro pessoal.

Cada serviço possui responsabilidade única, comunicação através do **API Gateway**, descoberta utilizando **Netflix Eureka** e autenticação baseada em **JWT**.

---

# 🏗️ Arquitetura

```text
                                +----------------------+
                                |     API Gateway      |
                                |      Porta 8080      |
                                +----------+-----------+
                                           |
              +----------------------------+----------------------------+
              |                             |                           |
              |                             |                           |
      +-------v-------+             +-------v--------+          +-------v-------+
      | Auth Service  |             | Transaction    |          | Report        |
      |   Porta 8081  |             | Service        |          | Service       |
      |               |             | Porta 8082     |          | Porta 8083    |
      +---------------+             +----------------+          +---------------+
                                           |
                                           |
                               +-----------v------------+
                               |    Service Registry    |
                               |  Netflix Eureka 8761   |
                               +------------------------+
```

---

# 🚀 Microserviços

| Serviço | Porta | Responsabilidade |
|----------|------:|------------------|
| 🌐 API Gateway | **8080** | Entrada da aplicação, roteamento e autenticação |
| 🔐 Auth Service | **8081** | Cadastro, login e autenticação |
| 💳 Transaction Service | **8082** | Receitas, despesas e movimentações financeiras |
| 📊 Report Service | **8083** | Relatórios e indicadores financeiros |
| 📡 Service Registry | **8761** | Descoberta e registro dos serviços |

---

# 🛠️ Tecnologias

## Backend

- Java 17
- Spring Boot
- Spring Cloud
- Spring Cloud Gateway
- Netflix Eureka
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- Maven

## Banco de Dados

- PostgreSQL

## Infraestrutura

- Docker
- Docker Compose

---

# 📂 Estrutura do Projeto

```text
financas-microservicos
│
├── api-gateway
├── service-registry
├── auth-service
├── transaction-service
├── report-service
│
├── docker-compose.yml
├── start-all.ps1
└── README.md
```

---

# 🚀 Como Executar

## Pré-requisitos

- Java 17
- Maven 3.9+
- Docker
- Docker Compose
- Git

---

## 1. Clonar o repositório

```bash
git clone https://github.com/adanwilliamdev/financas-microservicos.git

cd financas-microservicos
```

---

## 2. Subir o PostgreSQL

```bash
docker-compose up -d
```

---

## 3. Compilar todos os serviços

```bash
mvn clean install -DskipTests
```

---

## 4. Iniciar todos os microserviços

Windows

```powershell
.\start-all.ps1
```

Linux

```bash
./start-all.sh
```

---

# 🌐 Serviços

| Aplicação | URL |
|-----------|-----|
| Eureka Dashboard | http://localhost:8761 |
| API Gateway | http://localhost:8080 |
| Auth Service | http://localhost:8081 |
| Transaction Service | http://localhost:8082 |
| Report Service | http://localhost:8083 |

---

# 🔑 Usuários de Teste

| Usuário | Senha |
|---------|--------|
| admin@financas.com | admin123 |
| usuario@financas.com | usuario123 |

---

# 📡 Endpoints

## 🔐 Auth Service

| Método | Endpoint |
|---------|----------|
| GET | `/api/auth/health` |
| POST | `/api/auth/register` |
| POST | `/api/auth/login` |

---

## 💳 Transaction Service

| Método | Endpoint |
|---------|----------|
| GET | `/api/transacoes` |
| POST | `/api/transacoes` |
| GET | `/api/transacoes/saldo` |

---

## 📊 Report Service

| Método | Endpoint |
|---------|----------|
| GET | `/api/relatorios/resumo` |
| GET | `/api/relatorios/categorias` |

---

# 🐳 Docker

Subir containers

```bash
docker-compose up -d
```

Parar containers

```bash
docker-compose down
```

Visualizar logs

```bash
docker-compose logs -f
```

---

# 🔒 Segurança

- Autenticação com JWT
- Spring Security
- API Gateway protegido
- Validação de Tokens
- Senhas criptografadas com BCrypt

---

# 📈 Roadmap

- [x] Service Registry
- [x] API Gateway
- [x] Auth Service
- [x] Transaction Service
- [x] Report Service
- [ ] Config Server
- [ ] RabbitMQ
- [ ] OpenFeign
- [ ] Zipkin
- [ ] Prometheus
- [ ] Grafana
- [ ] Kubernetes

---

# 🤝 Contribuição

Contribuições são sempre bem-vindas!

1. Faça um Fork
2. Crie uma Branch

```bash
git checkout -b feature/minha-feature
```

3. Commit

```bash
git commit -m "feat: nova funcionalidade"
```

4. Push

```bash
git push origin feature/minha-feature
```

5. Abra um Pull Request

---

# 📄 Licença

Este projeto está licenciado sob a licença **MIT**.

---

<div align="center">

Desenvolvido com ❤️ utilizando Spring Boot e Spring Cloud.

</div>
