package br.com.zup.estrelas.loja.pojo;

public class VendaPojo {
	private int codigoBarra;
	private int quantidade;
	private double valorUnitario;
	private double valorTotal;
	
	public VendaPojo(int codigoBarra, int quantidade, double valorUnitario, double valorTotal) {
		this.codigoBarra = codigoBarra;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
	}

	public int getCodigoBarra() {
		return codigoBarra;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

}
