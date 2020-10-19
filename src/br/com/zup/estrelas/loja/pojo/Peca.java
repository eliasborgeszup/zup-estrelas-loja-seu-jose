package br.com.zup.estrelas.loja.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "peca")
public class Peca {
	@Id
	@Column(name = "codigo_de_barra")
	private int codigoBarra;
	private String nome;
	
	@Column(name = "modelo_carro")
	private String modeloCarro;
	private String fabricante;
	
	@Column(name = "preco_custo")
	private double precoCusto;
	
	@Column(name = "preco_venda")
	private double precoVenda;
	
	@Column(name = "quantidade_estoque")
	private int quantidadeEstoque;
	
	private String categoria;
	
	public Peca() {
		
	}
	
	public Peca(int codigoBarra, String nome, String modeloCarro, String fabricante, double precoCusto,
			double precoVenda, int quantidadeEstoque, String categoria) {
		this.codigoBarra = codigoBarra;
		this.nome = nome;
		this.modeloCarro = modeloCarro;
		this.fabricante = fabricante;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.quantidadeEstoque = quantidadeEstoque;
		this.categoria = categoria;
	}
	
	public int getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(int codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public double getPrecoCusto() {
		return precoCusto;
	}
	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}
	public double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
