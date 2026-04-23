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
