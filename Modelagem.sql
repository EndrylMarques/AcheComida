create table if not exists categorias(
	id serial primary key,
    descricao varchar(30)
);

create table if not exists  produtos(
	id serial primary key,
    descricao varchar(30),
    categoria_id integer,
    valor double precision
);

create table if not exists  usuarios(
	id serial primary key,
    nome varchar(30),
    email varchar(30),
    senha varchar(10),
    telefone varchar(12));

	
create table if not exists  vendedores(	
	id serial primary key,
    nome varchar(30),
    email varchar(30),
    senha varchar(10),
    telefone varchar(12),
	produto_id integer);

alter table produtos drop constraint if exists fk_categoria;
alter table vendedores drop constraint if exists fk_produto;


alter table produtos 
add CONSTRAINT fk_categoria 
FOREIGN KEY (categoria_id) 
references categorias (id);

alter table vendedores
add constraint fk_produto
FOREIGN KEY (produto_id)
references produtos (id);