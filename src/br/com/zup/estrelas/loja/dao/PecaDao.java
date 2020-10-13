package br.com.zup.estrelas.loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.zup.estrelas.loja.connection.factory.ConnectionFactory;
import br.com.zup.estrelas.loja.pojo.PecaPojo;

public class PecaDao {
	private Connection conexao;

	public PecaDao() {
		this.conexao = new ConnectionFactory().obterConexao();
	}

	public boolean cadastrarPecaBD(PecaPojo pecaPojo) {
		String cadastrarSql = "INSERT INTO peca (codigo_de_barra, nome, modelo_carro, fabricante, preco_custo, preco_venda, quantidade_estoque, categoria) \r\n"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = conexao.prepareStatement(cadastrarSql);

			stmt.setInt(1, pecaPojo.getCodigoBarra());
			stmt.setString(2, pecaPojo.getNome());
			stmt.setString(3, pecaPojo.getModeloCarro());
			stmt.setString(4, pecaPojo.getFabricante());
			stmt.setDouble(5, pecaPojo.getPrecoCusto());
			stmt.setDouble(6, pecaPojo.getPrecoVenda());
			stmt.setInt(7, pecaPojo.getQuantidadeEstoque());
			stmt.setString(8, pecaPojo.getCategoria());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			System.out.println("Não foi possivel cadastrar peça " + e.getMessage());
			return false;
		}

		return true;
	}

	public PecaPojo buscarPecaPorCodigoBarraBD(int codigoBarra) {
		String buscaPecaPorCodigoBarraSql = "SELECT * FROM peca WHERE codigo_de_barra = ?";
		
		PecaPojo pecaPojo = new PecaPojo();
		try {
			PreparedStatement stmt = conexao.prepareStatement(buscaPecaPorCodigoBarraSql);
			stmt.setInt(1, codigoBarra);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				pecaPojo.setCodigoBarra(rs.getInt("codigo_de_barra"));
				pecaPojo.setNome(rs.getString("nome"));
				pecaPojo.setModeloCarro(rs.getString("modelo_carro"));
				pecaPojo.setFabricante(rs.getString("fabricante"));
				pecaPojo.setPrecoCusto(rs.getDouble("preco_custo"));
				pecaPojo.setPrecoVenda(rs.getDouble("preco_venda"));
				pecaPojo.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
				pecaPojo.setCategoria(rs.getString("categoria"));
			}

		} catch (SQLException e) {
			System.out.println("Não foi possivel localizar codigo de barra " + e.getMessage());
		}

		return pecaPojo;
	}
	
	public List<PecaPojo> buscarPecasBD() {
		List<PecaPojo> pecas = new ArrayList<>();
		
		String buscaPecasSql = "SELECT * FROM peca";
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(buscaPecasSql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				PecaPojo pecaPojo = new PecaPojo();
				
				pecaPojo.setCodigoBarra(rs.getInt("codigo_de_barra"));
				pecaPojo.setNome(rs.getString("nome"));
				pecaPojo.setModeloCarro(rs.getString("modelo_carro"));
				pecaPojo.setFabricante(rs.getString("fabricante"));
				pecaPojo.setPrecoCusto(rs.getDouble("preco_custo"));
				pecaPojo.setPrecoVenda(rs.getDouble("preco_venda"));
				pecaPojo.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
				pecaPojo.setCategoria(rs.getString("categoria"));
				
				pecas.add(pecaPojo);
			}
			
		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar peca " + e.getMessage());
		}
		return pecas;
	}
}
