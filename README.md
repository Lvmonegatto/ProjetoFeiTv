# 🎬 FEItv - Catálogo Studio Ghibli

Sistema desktop desenvolvido em Java com arquitetura MVC e integração com PostgreSQL para gerenciamento de filmes do Studio Ghibli.

---

# 📌 Sobre o Projeto

O FEItv é uma aplicação desktop desenvolvida em Java Swing com persistência em PostgreSQL, focada no gerenciamento e visualização de filmes do Studio Ghibli.

O sistema permite:

- 👤 Cadastro e login de usuários
- 🔍 Busca de filmes
- 📖 Exibição detalhada de informações
- 👍 Curtir e 👎 descurtir filmes
- ⭐ Gerenciamento de favoritos
- 🎞️ Lista de reprodução personalizada
- 🗄️ Persistência completa em banco de dados PostgreSQL

---

# 🛠️ Tecnologias Utilizadas

- Java
- Java Swing
- PostgreSQL
- JDBC
- MVC (Model View Controller)
- NetBeans IDE

---

# 🚀 Funcionalidades

## 👤 Cadastro de Usuário

- Cadastro de novos usuários
- Validação de campos
- CPF formatado
- Persistência no banco de dados

---

## 🔐 Login de Usuário

- Autenticação via PostgreSQL
- Controle de sessão do usuário logado

---

## 🎬 Catálogo de Filmes

- Busca de filmes por nome
- JTable dinâmica
- Exibição de informações detalhadas:
  - Título
  - Categoria
  - Duração
  - Diretor
  - Ano
  - Data de lançamento
  - Descrição
  - URL

---

## 👍 Sistema de Avaliação

- Curtir filmes
- Descurtir filmes
- Controle de avaliação única por usuário
- Alternância automática entre LIKE e DESLIKE

---

## ⭐ Favoritos

- Adicionar filme aos favoritos
- Remover favoritos
- Controle de duplicidade
- Favoritos separados por usuário

---

## 🎞️ Lista de Reprodução

- Adicionar filmes favoritos à lista de reprodução
- Remover filmes da lista
- Visualização personalizada da lista

---

# 🧱 Estrutura do Projeto

```text
src/
│
├── Controller/
│   ├── CadastroController
│   ├── LoginController
│   ├── TelaPrincipalController
│   ├── FavoritosController
│   └── ListaReproducaoController
│
├── Model/
│   ├── Usuario
│   ├── Filmes
│   ├── Favorito
│   ├── AvaliacaoFilme
│   ├── ListaReproducao
│   └── Sessao
│
├── Model/Dao/
│   ├── Conexao
│   ├── UsuarioDAO
│   ├── FilmeDAO
│   ├── FavoritoDAO
│   ├── AvaliacaoFilmeDAO
│   └── ListaReproducaoDAO
│
└── View/
    ├── Login
    ├── Cadastro
    ├── TelaPrincipal
    ├── TelaFavoritos
    └── TelaListaReproducao
```

---

# 🗄️ Banco de Dados

## 📋 Tabela `usuarios`

```sql
CREATE TABLE usuarios (

    id_usuario SERIAL PRIMARY KEY,

    nome VARCHAR(100) NOT NULL,

    email VARCHAR(100) NOT NULL,

    cpf VARCHAR(14) NOT NULL,

    senha VARCHAR(100) NOT NULL
);
```

---

## 🎬 Tabela `filmes`

```sql
CREATE TABLE filmes (

    id_filme SERIAL PRIMARY KEY,

    titulo VARCHAR(200) NOT NULL,

    categoria VARCHAR(100) NOT NULL,

    duracao VARCHAR(20) NOT NULL,

    likes INT DEFAULT 0,

    deslikes INT DEFAULT 0,

    url VARCHAR(255),

    descricao TEXT,

    diretor VARCHAR(100),

    ano INT,

    data_lancamento DATE
);
```

---

## ⭐ Tabela `favoritos`

```sql
CREATE TABLE favoritos (

    id_favorito SERIAL PRIMARY KEY,

    id_usuario INT NOT NULL,

    id_filme INT NOT NULL,

    UNIQUE (id_usuario, id_filme),

    FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id_usuario),

    FOREIGN KEY (id_filme)
        REFERENCES filmes(id_filme)
);
```

---

## 👍 Tabela `avaliacao_filme`

```sql
CREATE TABLE avaliacao_filme (

    id_avaliacao SERIAL PRIMARY KEY,

    id_usuario INT NOT NULL,

    id_filme INT NOT NULL,

    tipo VARCHAR(10) NOT NULL,

    UNIQUE (id_usuario, id_filme),

    FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id_usuario),

    FOREIGN KEY (id_filme)
        REFERENCES filmes(id_filme)
);
```

---

## 🎞️ Tabela `lista_reproducao`

```sql
CREATE TABLE lista_reproducao (

    id_lista SERIAL PRIMARY KEY,

    id_usuario INT NOT NULL,

    id_filme INT NOT NULL,

    UNIQUE (id_usuario, id_filme),

    FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id_usuario),

    FOREIGN KEY (id_filme)
        REFERENCES filmes(id_filme)
);
```

---

# 🎥 Filmes Populados no Banco

O sistema possui inserts automáticos contendo os principais filmes do Studio Ghibli:

- Meu Amigo Totoro
- A Viagem de Chihiro
- O Castelo Animado
- Princesa Mononoke
- Ponyo
- O Serviço de Entregas da Kiki
- O Castelo no Céu
- Túmulo dos Vagalumes
- O Reino dos Gatos
- O Menino e a Garça
- Entre outros...

---

# 📌 Regras de Negócio

## ⭐ Favoritos

- Um usuário não pode favoritar o mesmo filme duas vezes

---

## 👍 Likes e 👎 Deslikes

- Um usuário pode:
  - Curtir
  - Descurtir
  - Remover avaliação

- Não é possível:
  - Curtir múltiplas vezes
  - Curtir e descurtir simultaneamente

---

## 🎞️ Lista de Reprodução

- Apenas filmes favoritados podem ser adicionados
- Não é possível duplicar filmes na lista

---

# ▶️ Como Executar

## 1️⃣ Clonar o repositório

```bash
git clone URL_DO_REPOSITORIO
```

---

## 2️⃣ Criar banco PostgreSQL

Criar um banco chamado:

```text
usuario
```

---

## 3️⃣ Executar scripts SQL

Executar:
- criação das tabelas
- constraints
- inserts dos filmes

---

## 4️⃣ Configurar conexão

Editar a classe:

```text
Conexao.java
```

com:
- usuário PostgreSQL
- senha
- porta

---

## 5️⃣ Executar o projeto

Executar:

```text
ProjetoFeiTv.java
```

---

# 👨‍💻 Autor
Luciano Ventura Monegatto
Projeto desenvolvido para fins acadêmicos utilizando Java Swing, PostgreSQL e arquitetura MVC.
