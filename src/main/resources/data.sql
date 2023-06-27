create table if not exists cliente
(
    nome varchar(255),
    id   bigint not null,
    primary key (id)
);

create table if not exists empresa
(
    cnpj        varchar(255),
    nome        varchar(255),
    status_loja varchar(255),
    id          bigint not null,
    primary key (id)
);
create table if not exists entregador
(
    nome varchar(255),
    id   bigint not null,
    primary key (id)
);
create table if not exists pedido
(
    id              bigint generated by default as identity,
    data_pedido     timestamp,
    forma_entrega   integer not null,
    forma_pagamento integer not null,
    status          varchar(255),
    cliente_id      bigint,
    empresa_id      bigint,
    entregador_id   bigint,
    primary key (id)
);
create table if not exists perfil
(
    id   bigint auto_increment not null,
    nome varchar(255),
    primary key (id)
);
create table if not exists produto
(
    id         bigint generated by default as identity,
    descricao  varchar(255),
    nome       varchar(255),
    quantidade integer,
    empresa_id bigint,
    pedido_id  bigint,
    primary key (id)
);
create table if not exists usuario
(
    id          bigint generated by default as identity,
    email       varchar(255),
    bairro      varchar(255),
    cep         varchar(255),
    complemento varchar(255),
    localidade  varchar(255),
    logradouro  varchar(255),
    numero      varchar(255),
    senha       varchar(255),
    primary key (id)
);
create table if not exists usuario_perfil
(
    usuario_id bigint not null,
    perfil_id  bigint not null
);
alter table cliente
    add constraint if not exists FKsitxst8o302fspskxfjatuyrl foreign key (id) references usuario;
alter table empresa
    add constraint if not exists FK1qxb7ae8vdagy0mb5p5pjifed foreign key (id) references usuario;
alter table entregador
    add constraint if not exists FKf1am2yyk87ooyyi2f4ab4usp2 foreign key (id) references usuario;
alter table pedido
    add constraint if not exists FK30s8j2ktpay6of18lbyqn3632 foreign key (cliente_id) references cliente;
alter table pedido
    add constraint if not exists FKt7rqpuedt7235kbgks5vdtbwi foreign key (empresa_id) references empresa;
alter table pedido
    add constraint if not exists FK5x80cwcdtp01tkevk2ftt0b6b foreign key (entregador_id) references entregador;
alter table produto
    add constraint if not exists FKhd2qcuv0aepvey17rumtskj9t foreign key (empresa_id) references empresa;
alter table produto
    add constraint if not exists FKdyammip6pdmju0ey2wuefbp5x foreign key (pedido_id) references pedido;
alter table usuario_perfil
    add constraint if not exists FK22cgfn0obntlvqyfn33pyk24d foreign key (perfil_id) references perfil;
alter table usuario_perfil
    add constraint if not exists FKnrjqnbylalt4ykxbcef24f57w foreign key (usuario_id) references usuario;


INSERT INTO perfil(id, nome)
    SELECT 1, 'CLIENTE'
WHERE NOT EXISTS(
        SELECT 1 FROM perfil where id = 1
    );
INSERT INTO perfil(id, nome)
SELECT 2, 'EMPRESA'
WHERE NOT EXISTS(
        SELECT 1 FROM perfil where id = 2
    );

INSERT INTO perfil(id, nome)
SELECT 3, 'ENTREGADOR'
WHERE NOT EXISTS(
        SELECT 1 FROM perfil where id = 3
    );
INSERT INTO perfil(id, nome)
SELECT 4, 'ADMIN'
WHERE NOT EXISTS(
        SELECT 1 FROM perfil where id = 4
    );

insert into usuario(email, bairro, cep, complemento, localidade, logradouro, numero, senha)
SELECT
       'emailCliente',
       'ads',
       'asdsad',
       'sadsdasad',
       'dsaasd',
       'sadasd',
       '213213',
       '$2a$10$./f1RQvh4Uo3rSsiOetDMeCKHsorsHzez7Z7najauKoBUABAPLRVy'
WHERE NOT EXISTS(
        SELECT 1 FROM usuario where id = 1
    );


insert into usuario(email, bairro, cep, complemento, localidade, logradouro, numero, senha)
SELECT
       'emailEmpresa',
       'ads',
       'asdsad',
       'sadsdasad',
       'dsaasd',
       'sadasd',
       '213213',
       '$2a$10$./f1RQvh4Uo3rSsiOetDMeCKHsorsHzez7Z7najauKoBUABAPLRVy'
WHERE NOT EXISTS(
        SELECT 1 FROM usuario where id = 2
    );


insert into usuario(email, bairro, cep, complemento, localidade, logradouro, numero, senha)
SELECT
       'emailEntregador',
       'ads',
       'asdsad',
       'sadsdasad',
       'dsaasd',
       'sadasd',
       '213213',
       '$2a$10$./f1RQvh4Uo3rSsiOetDMeCKHsorsHzez7Z7najauKoBUABAPLRVy'
WHERE NOT EXISTS(
        SELECT 1 FROM usuario where id = 3
    );

insert into usuario(email, bairro, cep, complemento, localidade, logradouro, numero, senha)
SELECT
    'admin',
    'ads',
    'asdsad',
    'sadsdasad',
    'dsaasd',
    'sadasd',
    '213213',
    '$2a$10$./f1RQvh4Uo3rSsiOetDMeCKHsorsHzez7Z7najauKoBUABAPLRVy'
WHERE NOT EXISTS(
        SELECT 1 FROM usuario where id = 4
    );


insert INTO usuario_perfil(usuario_id, perfil_id)
SELECT 1, 1;

insert INTO usuario_perfil(usuario_id, perfil_id)
SELECT 2, 2;
insert INTO usuario_perfil(usuario_id, perfil_id)
SELECT 3, 3;

insert INTO usuario_perfil(usuario_id, perfil_id)
SELECT 4, 4;