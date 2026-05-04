CREATE SCHEMA IF NOT EXISTS forkup;

--- Criando as tabelas

CREATE TABLE IF NOT EXISTS forkup.status (
    id INTEGER NOT NULL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS forkup.tipo_usuario (
    id INTEGER NOT NULL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL,
    status_id INTEGER NOT NULL,
    CONSTRAINT fk_tipo_usuario_status FOREIGN KEY (status_id) REFERENCES forkup.status(id)
);

CREATE TABLE IF NOT EXISTS forkup.usuario(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    login VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    tipo_usuario_id INTEGER NOT NULL,
    status_id INTEGER NOT NULL,
    CONSTRAINT fk_usuario_tipo_usuario FOREIGN KEY (tipo_usuario_id) REFERENCES forkup.tipo_usuario (id),
    CONSTRAINT fk_usuario_status FOREIGN KEY (status_id) REFERENCES forkup.status (id)
);

CREATE TABLE IF NOT EXISTS forkup.endereco(
    id BIGSERIAL PRIMARY KEY,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    complemento VARCHAR(255),
    cidade VARCHAR(255) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_id BIGINT NOT NULL,
    status_id INTEGER NOT NULL,
    CONSTRAINT fk_endereco_usuario FOREIGN KEY (usuario_id) REFERENCES forkup.usuario (id),
    CONSTRAINT fk_endereco_status FOREIGN KEY (status_id) REFERENCES forkup.status (id)
);

INSERT INTO forkup.status (id, descricao) VALUES
    (1, 'ATIVO'),
    (2, 'EXCLUIDO'),
    (3, 'INATIVO');

INSERT INTO forkup.tipo_usuario (id, descricao, status_id) VALUES
    (1, 'Dono de Restaurante', 1),
    (2, 'Cliente', 1);


-- Criando Usuários para exemplos nos endpoints
INSERT INTO forkup.usuario (nome, email, login, senha, tipo_usuario_id, status_id) VALUES
    ('Dono de Restaurante da Silva', 'dono.restaurante@gmail.com', 'donoRestaurante23', '$2a$12$yJgut64canhS.kTlZp7SFOy6h6dx0227pfMkLJtbwPJgvJp.iNJIW', 1, 1),
    ('Cliente da Silva', 'cliente@gmail.com', 'cliente45', '$2a$12$G9T.aYTQMtwAvbq.eFab8enbM95jYj/w0hlrOiBMYBTIPCBqml8uW', 2, 1),
    ('Arthur Pereira', 'arthur@gmail.com', 'Arthurzin', '$2a$12$.x7J7TUnhTSATzFlby2tcuesFZDmTRvfFrf0f/iBG9dPClMsDzjBe', 2, 1),
    ('Usuário Erros Duplicado', 'usuario.erros.duplicados@gmail.com', 'usuarioErrosDuplicados', '$2a$12$64lfVjrzOlq/rP3lO4avYu5LSHwpRCkG9Ek6u007MwGJ3ttmCTJy6', 2, 1);

-- Criando Endereços para exemplos nos endpoints
INSERT INTO forkup.endereco (logradouro, numero, complemento, cidade, cep, usuario_id, status_id) VALUES
    ('Rua das Flores', '123', 'Apto 456', 'São Paulo', '01234-567', 1, 1),
    ('Avenida Brasil', '456', 'Casa', 'Rio de Janeiro', '23456-789', 3, 1),
    ('Rua dos Pinheiros', '789', NULL, 'Belo Horizonte', '34567-890', 4, 1);
