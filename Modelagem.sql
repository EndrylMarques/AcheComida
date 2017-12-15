create table categorias(
	id serial primary key,
    descricao varchar(30)
);

create table produtos(
	id serial primary key,
    descricao varchar(30),
    categoria_id integer,
    valor double precision
);

create table usuarios(
	id serial primary key,
    nome varchar(30),
    email varchar(30),
    senha varchar(10),
    telefone varchar(12));

alter table produtos 
add CONSTRAINT fk_categoria 
FOREIGN KEY (categoria_id) 
references categorias (id);

