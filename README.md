# PicPay Simplificado - Desafio Backend

Esta é uma resolução do desafio técnico público do PicPay, focado em simular uma plataforma de pagamentos simplificada. O projeto foi desenvolvido com fins de estudo e prática de boas práticas de desenvolvimento backend com Java e Spring Boot.

🔗 **Link do Desafio Original:** [PicPay Desafio Backend](https://github.com/PicPay/picpay-desafio-backend)

---

## 🚀 Sobre o Projeto

O **PicPay Simplificado** permite que usuários realizem transferências de dinheiro entre si. O sistema lida com dois tipos de usuários: **Comuns** e **Lojistas**.

### Regras de Negócio Implementadas:
* **Cadastro:** Usuários possuem Nome Completo, CPF/CNPJ (únicos), E-mail (único) e Senha.
* **Transferências:** * Usuários comuns podem enviar e receber dinheiro.
    * Lojistas **apenas recebem** transferências.
* **Validação de Saldo:** O sistema impede transferências se o pagador não tiver saldo suficiente.
* **Autorizador Externo:** Antes de concluir, a transação consulta um serviço mockado de autorização.
* **Notificações:** Após a transferência, o sistema simula o envio de notificações aos envolvidos.
* **Transacionalidade:** Em caso de erro crítico, a operação é revertida (Rollback) para garantir a integridade dos dados.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3
* **Banco de Dados:** H2 (Banco de dados em memória para facilitar a execução)
* **Persistência:** Spring Data JPA / Hibernate
* **Ferramentas:** Lombok, RestTemplate (Chamadas HTTP), Maven

---

## 🏗️ Arquitetura e Boas Práticas

O projeto foi estruturado seguindo princípios de código limpo e organização por camadas:
1.  **Controllers:** Portas de entrada da API (REST).
2.  **Services:** Onde reside toda a lógica de negócio e validações.
3.  **Repositories:** Interface de comunicação com o banco de dados.
4.  **DTOs (Data Transfer Objects):** Utilizados para tráfego seguro de dados entre camadas, evitando exposição de entidades do banco.

---

## 🏁 Como Executar

1.  Clone o repositório:
    ```bash
    git clone [https://github.com/ArthurVSMatos/picpay-desafio-backend.git](https://github.com/ArthurVSMatos/picpay-desafio-backend.git)
    ```
2.  Importe o projeto na sua IDE preferida (IntelliJ, Eclipse ou VS Code).
3.  Aguarde o Maven baixar as dependências.
4.  Execute a classe principal `DesafioBackAndApplication`.
5.  A API estará disponível em `http://localhost:8080`.

### Acesso ao Banco de Dados (H2 Console)
Você pode visualizar as tabelas em tempo real acessando:
* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:testdb`
* **User:** `sa` | **Password:** (em branco)

---

## 📬 Endpoints Principais

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/users` | Cadastra um novo usuário (Common ou Merchant) |
| `GET` | `/users` | Lista todos os usuários cadastrados |
| `POST` | `/transfer` | Realiza uma transferência entre dois usuários |

---
*Este projeto foi desenvolvido como parte de um portfólio pessoal de estudos.*
