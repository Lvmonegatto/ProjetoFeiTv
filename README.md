# ProjetoFeiTv
# 🎬 FEItv - Plataforma de Informações de Vídeos

## 📌 Descrição do Projeto

O **FEItv** é um sistema desenvolvido com o objetivo de gerenciar e disponibilizar informações sobre vídeos, como filmes e séries. A proposta é simular a lógica de plataformas de streaming (como Netflix e YouTube), porém **sem a reprodução de vídeos**, focando exclusivamente na organização, busca e interação com conteúdos.

---

## 🎯 Objetivo

Construir uma aplicação que permita aos usuários:

* Buscar informações sobre vídeos
* Interagir com conteúdos (curtir/descurtir)
* Organizar vídeos em listas de favoritos personalizadas

---

## 🚀 Funcionalidades

### 👤 Usuário

* Cadastro de novos usuários
* Login no sistema

### 🔍 Vídeos

* Buscar vídeos pelo nome
* Listar informações detalhadas dos vídeos

### 👍 Interações

* Curtir vídeos
* Descurtir vídeos

### ⭐ Favoritos (Playlists)

* Criar listas de vídeos favoritos
* Editar listas de favoritos
* Excluir listas de favoritos
* Adicionar vídeos às listas
* Remover vídeos das listas

---

## 🛠️ Tecnologias Utilizadas

*(ajuste conforme seu projeto)*

* Java
* PostgreSQL
* JDBC
* pgAdmin
* (Opcional: JavaFX / Swing para interface)
  
---

## 🧠 Modelagem do Sistema

O sistema é baseado em:

* Entidade **Usuário**
* Entidade **Vídeo**
* Relacionamento de **Curtidas**
* Sistema de **Listas de Favoritos (Playlists)**

---

## 🗄️ Banco de Dados (Exemplo)

Tabela de usuários:

```sql
CREATE TABLE usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    datanascimento DATE NOT NULL
);
```

---

## ▶️ Como Executar

1. Criar o banco de dados no PostgreSQL
2. Executar o script SQL
3. Configurar conexão no projeto Java
4. Executar a aplicação

---

## 📌 Observações

* O sistema **não reproduz vídeos**, apenas gerencia informações
* Senhas devem ser armazenadas com segurança (hash)
* O projeto tem foco acadêmico e aprendizado de conceitos como:

  * Modelagem de banco de dados
  * CRUD
  * Relacionamentos
  * Arquitetura em camadas (DAO, Model, Controller)

---

## 👨‍💻 Autor

Projeto desenvolvido para fins acadêmicos.
