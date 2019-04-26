CREATE TABLE cliente (

	id_cliente SERIAL PRIMARY KEY,
	nome_cliente VARCHAR(50) NOT NULL,
	cpf_cliente BIGINT NOT NULL,
	email_cliente VARCHAR(80)
);

CREATE TABLE lancamento (

	id_lancamento SERIAL PRIMARY KEY,
	id_cliente_lancamento INTEGER NOT NULL,
	data_lancamento DATE NOT NULL,
	valor_lancamento FLOAT NOT NULL,
	tipo_lancamento INTEGER NOT NULL,
	FOREIGN KEY (id_cliente_lancamento) REFERENCES cliente (id_cliente)
);

CREATE TABLE usuario (
	username_usuario VARCHAR(15) PRIMARY KEY,
	password_usuario VARCHAR(15) NOT NULL
);
