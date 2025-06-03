# 🐶🐱 Cadastro de Pets

🚀 Projeto desenvolvido para gerenciar um cadastro de pets, utilizando **Java**, **Spring Boot**, **MySQL** e **Docker**.  
Aqui você pode criar, listar, atualizar e excluir registros de pets através de uma API REST. Testado com o **Postman**.

---

## 🛠️ Tecnologias Utilizadas

- ☕ **Java**
- 🌱 **Spring Boot**
- 🐳 **Docker** (usado para o banco de dados)
- 🐬 **MySQL**
- 📫 **Postman** (para testes da API)
- ⚙️ **Maven** (para gerenciamento de dependências)
- 🧪 **jUnit e Mockito** (Para testes unitários e simulações)
- ⚠️ **Tratamento de exceções**
---

## ⚙️ Pré-requisitos

- 🔸 Docker 
- 🔸 Java 24 ☕
- 🔸 Maven 📦 (para gerenciar dependências e rodar o projeto localmente)
- 🔸 Postman (ou outra ferramenta de API)

---

## 🚀 Como Rodar o Projeto

### 🐳 Usando Docker para o Banco

1. 🔥 Clone este repositório:
   ```bash
   git clone https://github.com/Nathaandev/desafioCadastro
   cd desafioCadastro

2.    🐬 Suba o banco de dados MySQL com Docker:
      docker-compose up -d

3.    ☕ Rode a Aplicação:
      Abra o projeto na sua IDE favorita (IntelliJ, Eclipse, VS Code) e execute a classe principal anotada com @SpringBootApplication.
  

## 📑 Endpoints Disponíveis

| Método    | Endpoint         | Descrição                                                                              |
|-----------|------------------|----------------------------------------------------------------------------------------|
| 🔍 GET    | `api/pets`          | Lista todos os pets. Também permite filtrar combinando **até 2** destes campos: id, Nome, Sobrenome, Sexo, Idade, Peso, Raça e Endereço. Exemplo: `/pets?name=Rex&gender=Masc`. |
| ➕ POST   | `api/cadastro`          | Cadastra um novo pet                                                                   |
| ♻️ PUT    | `api/update/{id}`     | Atualiza os dados de um pet                                                            |
| ❌ DELETE | `api/delete/{id}`     | Remove um pet                                                                          |

**💾 Configurações do Banco de Dados**

Banco: MySQL

Porta: 3306

Banco de dados: cadastropets

Usuário: root

Senha: rootd

As configurações estão no arquivo application.properties e no docker-compose.yml.

📤 Modelo JSON

```json
{
  "firstname": "",
  "lastname": "",
  "race": "",
  "street": "",
  "number": "",
  "city": "",
  "weight": "",
  "type": "",  // Cat ou Dog
  "gender": "",  // Masc ou Fem
  "age": ""
}


