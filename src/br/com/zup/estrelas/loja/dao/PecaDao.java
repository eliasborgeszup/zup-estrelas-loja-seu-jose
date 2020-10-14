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
				pecaPojo = montarObjeto(rs);
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
				PecaPojo pecaPojo = montarObjeto(rs);

				pecas.add(pecaPojo);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar peca " + e.getMessage());
		}
		return pecas;
	}

	public List<PecaPojo> buscarPecasPorNomeBD(String nome) {
		List<PecaPojo> pecas = new ArrayList<>();

		String buscaPecasPorNomeSql = "SELECT * FROM peca WHERE nome LIKE ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(buscaPecasPorNomeSql);

			stmt.setString(1, nome + '%');

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PecaPojo pecaPojo = montarObjeto(rs);

				pecas.add(pecaPojo);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar peca " + e.getMessage());
		}

		return pecas;
	}

	public PecaPojo montarObjeto(ResultSet rs) throws SQLException {
		PecaPojo pecaPojo = new PecaPojo();

		pecaPojo.setCodigoBarra(rs.getInt("codigo_de_barra"));
		pecaPojo.setNome(rs.getString("nome"));
		pecaPojo.setModeloCarro(rs.getString("modelo_carro"));
		pecaPojo.setFabricante(rs.getString("fabricante"));
		pecaPojo.setPrecoCusto(rs.getDouble("preco_custo"));
		pecaPojo.setPrecoVenda(rs.getDouble("preco_venda"));
		pecaPojo.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
		pecaPojo.setCategoria(rs.getString("categoria"));

		return pecaPojo;
	}

	public List<PecaPojo> buscarPecasPorModeloBD(String modelo){
		List<PecaPojo> pecas = new ArrayList<>();

		String buscaPecasPorModeloSql = "SELECT * FROM peca WHERE modelo_carro = ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(buscaPecasPorModeloSql);

			stmt.setString(1, modelo);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PecaPojo pecaPojo = montarObjeto(rs);

				pecas.add(pecaPojo);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar peca " + e.getMessage());
		}

		return pecas;
	}
	
	public List<PecaPojo> buscarPecasPorCategoriaBD(String categoria){
		List<PecaPojo> pecas = new ArrayList<>();

		String buscaPecasPorCategoriaSql = "SELECT * FROM peca WHERE categoria = ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(buscaPecasPorCategoriaSql);

			stmt.setString(1, categoria);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PecaPojo pecaPojo = montarObjeto(rs);

				pecas.add(pecaPojo);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possivel buscar peca " + e.getMessage());
		}

		return pecas;
	}

	public boolean excluirPecaBD(int codigoBarra) {
		String removerPecaPorCodigoBarraSql = "DELETE FROM peca WHERE codigo_de_barra = ?";
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(removerPecaPorCodigoBarraSql);
			stmt.setInt(1, codigoBarra);
			
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			System.out.printf("Não foi possivel excluir a peça, tente novamente\n");
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean modificarQuantidadePeca(PecaPojo pecaPojo, int quantidade) {
		String modificaPecaPorCodigoDeBarra = "UPDATE peca SET quantidade_estoque = ? WHERE codigo_de_barra = ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(modificaPecaPorCodigoDeBarra);
			stmt.setInt(1, pecaPojo.getQuantidadeEstoque() - quantidade);
			stmt.setInt(2, pecaPojo.getCodigoBarra());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Não foi possivel modificar a quantidade da peça " + e.getMessage());
			return false;
		}

		return true;
	}
}
