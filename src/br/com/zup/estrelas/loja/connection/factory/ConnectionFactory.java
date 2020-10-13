package br.com.zup.estrelas.loja.connection.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection obterConexao() {
		try {
			System.out.println("Conexao realizado com sucesso");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/banco_loja_pecas?user=root&password=root&useTimezone=true&serverTimezone=UTC");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
