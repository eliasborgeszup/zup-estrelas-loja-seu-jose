package br.com.zup.estrelas.loja.dao;

import java.sql.Connection;

import br.com.zup.estrelas.loja.connection.factory.ConnectionFactory;

public class LojaDao {
	private Connection conexao;

	public LojaDao() {
		this.conexao = new ConnectionFactory().obterConexao();
	}
}
