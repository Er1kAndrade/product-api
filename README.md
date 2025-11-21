# product-api

Uma API simples para gerenciamento de produtos, desenvolvida em Java.  
Este projeto foi criado com o objetivo de demonstrar uma solução back-end para cadastro, consulta, atualização e remoção de produtos.

---

## 🔍 Visão Geral

- **Linguagem:** Java  
- **Build:** Maven  
- **Execução:** Docker / Docker Compose (opcional)  
- **Proposta:** Servir como base para estudo, portfolio ou uma API inicial para projetos maiores  

---

## 📁 Estrutura do Projeto

- `pom.xml` — configura dependências e plugins do Maven  
- `docker-compose.yml` — serviços da aplicação e banco (se aplicável)  
- `src/main/java` — código fonte principal  
- `src/main/resources` — configurações e properties  
- `src/test/java` — testes automatizados (se existirem)  
- `.mvn/`, `mvnw`, `mvnw.cmd` — Maven Wrapper

---

## 🚀 Pré-Requisitos

Certifique-se de ter:

- **Java** (versão compatível usada no projeto)  
- **Maven** instalado ou o Maven Wrapper (`./mvnw`)  
- **Docker e Docker Compose** caso deseje levantar via contêiner  
- (Opcional) Banco de dados configurado no `application.properties`

---

## 🧑‍💻 Como executar

### ▶️ Executar via Maven

```bash
# Clonar o repositório
git clone https://github.com/Er1kAndrade/product-api.git
cd product-api

# Compilar o projeto
./mvnw clean install

# Rodar a API
./mvnw spring-boot:run

- 🐳 Executar via Docker Compose

- git clone https://github.com/Er1kAndrade/product-api.git
- cd product-api

- docker-compose up

- Isso irá subir a aplicação e eventuais serviços definidos no arquivo docker-compose.yml.


📡 Endpoints principais

    Os endpoints podem variar conforme sua implementação. Ajuste conforme necessário.

    GET /products — lista todos os produtos

    GET /products/{id} — busca produto pelo ID

    POST /products — cria um novo produto

    PUT /products/{id} — atualiza um produto existente

    DELETE /products/{id} — exclui um produto

✅ Funcionalidades

    CRUD completo de Produtos

    Tratamento básico de erros

    Uso de boas práticas REST

    Organização clara de pacotes

    Suporte opcional a Docker

✍️ Autor

Erik Andrade
GitHub: https://github.com/Er1kAndrade
Contribuições, issues e sugestões são bem-vindas!