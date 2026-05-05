# 🚀 ForkUp API

Projeto do Tech Challenge da 1ª fase da Pós-Graduação em **Arquitetura e Desenvolvimento Java**.

👨‍💻 Desenvolvido por: **LuisFelipeGM - RM: 371055**

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring](https://img.shields.io/badge/SpringBoot-green)
![Docker](https://img.shields.io/badge/Docker-blue)
---

# 🎯 Visão Geral

A 1° versão do **ForkUp** é uma API REST desenvolvida em Java com Spring Boot, focada em:

- ✅ Gerenciamento de usuários (CRUD)
- ✅ Gerenciamento de endereços (CRUD)
- ✅ Autenticação básica (login)
- ✅ Validação robusta de dados
- ✅ Paginação de resultados
- ✅ Documentação automática via Swagger

---

# 🛠️ Tecnologias

## Backend
- Java 21
- Spring Boot
- Spring Security *(criptografia de senha)*
- Spring Data JPA
- Hibernate

## Banco de Dados
- PostgreSQL 15
- Flyway (migrations)

## Ferramentas
- MapStruct
- Lombok
- SpringDoc OpenAPI (Swagger)
- Gradle

## DevOps
- Docker
- Docker Compose
- JUnit

---

# 🐳 Arquitetura Docker

A aplicação é composta por dois serviços:

- **app** → API Spring Boot
- **postgres** → Banco de dados PostgreSQL

A comunicação entre eles ocorre via **rede interna do Docker**.

---

# ⚙️ Configuração

## 📋 Pré-requisitos

- Docker e Docker Compose

---

### 1. Clonar o Repositório

```bash
git clone https://github.com/LuisFelipeGM/ForkUp
cd ForkUp
```
---

### 2. Configurar variáveis de ambiente

Copie o arquivo de exemplo:

```env
cp .env.example .env
```
No Windows (PowerShell):
```powershell
copy .env.example .env
```

Edite o arquivo `.env`:

```env
DB_NAME=forkup_db
DB_USER=admin
DB_PASSWORD=sua_senha
DB_PORT=5432
APP_PORT=3710
```
---
# 🚀 Execução 

## 🐳 Opção 1: Docker Compose (Recomendado)


```bash
docker compose up --build
```

A aplicação estará disponível em: `http://localhost:3710` *(Caso não tenha alterado a porta no .env)*

---
## 📦 Opção 2: Usando imagem do Docker Hub

```bash
docker pull luisfelipegm/forkup-app:latest
```

Executando manualmente:
```bash
docker run -p 3710:3710 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/forkup_db \
  -e SPRING_DATASOURCE_USERNAME=forkup_user \
  -e SPRING_DATASOURCE_PASSWORD=senha123 \
  luisfelipegm/forkup-app:latest
```
⚠️ Necessário ter um container PostgreSQL rodando.

---

# 🔌 API e Testes

## 📄 Swagger (Documentação interativa)
Acesse:

👉 http://localhost:3710/swagger-ui.html

---

## 🧪 Postman Collection

Uma collection está disponível no projeto:
- **Arquivo**: [`ForkUp_postman_collection.json`](ForkUp_postman_collection.json)
- Importe no Postman para testar todos os endpoints com cenários prontos.

---

# 🗄️ Banco de Dados

As migrações são gerenciadas automaticamente pelo Flyway:

```
src/main/resources/db/migration/
└── V001__init_forkup.sql
```

---

# 🔐 Segurança
- Senhas criptografadas com Spring Security
- Uso de variáveis de ambiente (`.env`)
- O arquivo `.env` não é versionado. Utilize `.env.example` como base.

---

## 🏗️ Estrutura do Projeto

O projeto segue um modelo em camadas:

```
com.fiap.forkup/
├── annotation/          # Anotações customizadas
├── config/              # Configurações (Security, OpenAPI)
├── constants/           # Constantes da aplicação
├── controller/
│   ├── v1/              # Controllers da API v1
│   └── v2/              # Controllers da API v2 (futura)
├── domain/
│   ├── dto/             # Data Transfer Objects
│   ├── entity/          # Entidades JPA
│   ├── enumeration/     # Enums
│   ├── vo/              # Value Objects
│   └── converter/       # Conversores de Entidades para Enums
├── exception/
│   ├── dto/             # DTOs de erro
│   ├── factory/         # Factory de exceções
│   └── handler/         # Handlers de exceção
├── mapper/              # Mappers MapStruct
├── repository/          # Repositórios JPA
├── service/             # Serviços de negócio
└── validations/         # Validadores customizados
```
---

# 📄 Licença
- Este projeto é acadêmico e de uso educacional.


