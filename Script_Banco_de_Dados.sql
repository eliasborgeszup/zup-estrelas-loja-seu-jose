CREATE DATABASE banco_loja_pecas;

CREATE TABLE banco_loja_pecas.peca(
		codigo_de_barra INT NOT NULL PRIMARY KEY,
		nome VARCHAR(45) UNIQUE NOT NULL,
        modelo_carro VARCHAR (45) NOT NULL,
		fabricante VARCHAR(45) NOT NULL,
        preco_custo DECIMAL(10, 2) NOT NULL,
        preco_venda DECIMAL(10, 2) NOT NULL,
        quantidade_estoque INT NOT NULL DEFAULT 0,
		categoria ENUM ('funilaria', 'motor', 'perfomace', 'som', 'roda') NOT NULL
);

SELECT * FROM banco_loja_pecas.peca;

INSERT INTO banco_loja_pecas.peca (codigo_de_barra, nome, modelo_carro, fabricante, preco_custo, preco_venda, quantidade_estoque, categoria) 
VALUES(0002, "Filtro de Ar G1", "Gol", "VW", 10.02, 10.98, 10, 'motor');

INSERT INTO banco_loja_pecas.peca
VALUES(0003, "Cubo Roda", "Fiat Uno", "Fiat", 48.81, 51.38, 10, 'roda');

INSERT INTO banco_loja_pecas.peca
VALUES(0004, "Filtro de Combustivel", "Hilux", "Toyota", 20.81, 25.98, 0, 'motor');

INSERT INTO banco_loja_pecas.peca
VALUES(0005, "Coxim", "Saveiro", "VW", 62.81, 75.26, 5, 'motor');

INSERT INTO banco_loja_pecas.peca
VALUES(0006, "Pastilha", "Strada", "Fiat", 37.53, 54.00, 20, 'roda');

INSERT INTO banco_loja_pecas.peca
VALUES(0004, 'motor', "Toyota", "Hilux", "Filtro de Combustivel", 20.81, 25.98, 2500);

INSERT INTO banco_loja_pecas.peca
VALUES(0003, 'roda', "Fiat", "Fiat Uno", "Cubo de roda", 48.81, 75.00, 7500);

INSERT INTO banco_loja_pecas.peca
VALUES(0005, 'roda', "Fiat", "Strada", "Pastilha", 37.53, 65.00, 1500);

INSERT INTO banco_loja_pecas.peca
VALUES(0006, 'motor', "VW", "Gol", "Filtro de Ar G1", 10.02, 17.00, 500);
