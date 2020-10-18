package br.com.zup.estrelas.loja;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import br.com.zup.estrelas.loja.dao.PecaDao;
import br.com.zup.estrelas.loja.pojo.PecaPojo;
import br.com.zup.estrelas.loja.pojo.VendaPojo;

public class ProgramaPrincipal {

	private static final String MENU_PRINCIPAL = ("======= BEM VINDO AO SISTEMA DE PE�AS - LOJA DO SEU JOSE =======\n"
			+ "[1] - Gest�o de pe�as\n" + "[2] - Gest�o de vendas\n" + "[0] - Sair\n");

	private static final String MENU_GESTAO_PECA = ("\n[1] - Cadastrar uma nova pe�a\n"
			+ "[2] - Consultar pe�a por c�digo de barra\n" + "[3] - Listar todas pe�as em estoque\n"
			+ "[4] - Listar pe�as por nome\n" + "[5] - Listar pe�as por modelo\n" + "[6] - Listar pe�as por categoria\n"
			+ "[7] - Remover pe�a do estoque\n" + "[0] - Voltar menu principal\n");

	private static final String MENU_GESTAO_VENDA = ("\n[1] - Realizar venda \n[2] - Extrair relat�rio de vendas\n"
			+ "[0] - Voltar menu principal\n");

	private static final String CATEGORIA_EXISTE = ("========== CATEGORIAS EXISTENTES ==========\n")
			+ ("[funilaria] [motor] [perfomace] [som] [roda]");

	public static void cadastrarPeca(Scanner teclado) {
		System.out.print("Digite o codigo de barra: ");
		int codigoBarra = teclado.nextInt();
		
		while(codigoBarra <= 0) {
			System.out.print("Ops .. codigo de barra invalido, digite novamente: ");
			codigoBarra = teclado.nextInt();
		}
		
		teclado.nextLine();
		System.out.print("Digite o nome da pe�a: ");
		String nome = teclado.nextLine();

		System.out.print("Digite o modelo do carro: ");
		String modeloCarro = teclado.nextLine();

		System.out.print("Digite o nome do fabricante: ");
		String fabricante = teclado.nextLine();

		System.out.print("Digite o pre�o de custo: R$");
		double precoCusto = teclado.nextDouble();

		System.out.print("Digite o pre�o de venda: R$");
		double precoVenda = teclado.nextDouble();

		System.out.print("Digite a quantidade em estoque: ");
		int quantidadeEstoque = teclado.nextInt();

		teclado.nextLine();
		System.out.print("Digite a categoria: ");
		String categoria = teclado.nextLine();

		PecaPojo pecaPojo = new PecaPojo(codigoBarra, nome, modeloCarro, fabricante, precoCusto, precoVenda,
				quantidadeEstoque, categoria);

		PecaDao pecaDao = new PecaDao();

		if (pecaDao.cadastrarPecaBD(pecaPojo)) {
			System.out.println("\nPe�a cadastrada com sucesso!\n");
		}
	}

	public static void buscarPecaPorCodigoBarra(Scanner teclado) {
		System.out.print("Digite o codigo de barra: ");
		int codigoBarra = teclado.nextInt();

		PecaDao pecaDao = new PecaDao();
		PecaPojo pecaPojo = pecaDao.buscarPecaPorCodigoBarraBD(codigoBarra);

		System.out.printf(
				"\nNome: %s Codigo de barra: %d Modelo: %s Fabricante: %s "
						+ "Pre�o Custo: R$%.2f Pre�o Venda: R$%.2f Quantidade: %d Categoria: %s",
				pecaPojo.getNome(), pecaPojo.getCodigoBarra(), pecaPojo.getModeloCarro(), pecaPojo.getFabricante(),
				pecaPojo.getPrecoCusto(), pecaPojo.getPrecoVenda(), pecaPojo.getQuantidadeEstoque(),
				pecaPojo.getCategoria());
	}

	public static void buscarPecaEstoque(Scanner teclado) {
		PecaDao pecaDao = new PecaDao();
		List<PecaPojo> pecasPojo = pecaDao.buscarPecasBD();

		imprimirPecas(pecasPojo);
	}

	public static void imprimirPecas(List<PecaPojo> pecasPojo) {
		for (PecaPojo pecaPojo : pecasPojo) {
			System.out.printf(
					"\nNome: %s | Codigo de barra: %d | Modelo: %s | Fabricante: %s | "
							+ "Pre�o Custo: R$%.2f | Pre�o Venda: R$%.2f | Quantidade: %d | Categoria: %s |\n",
					pecaPojo.getNome(), pecaPojo.getCodigoBarra(), pecaPojo.getModeloCarro(), pecaPojo.getFabricante(),
					pecaPojo.getPrecoCusto(), pecaPojo.getPrecoVenda(), pecaPojo.getQuantidadeEstoque(),
					pecaPojo.getCategoria());
			System.out.println("=================================================================================");
		}
	}

	public static void imprimirRelatorioVendas(List<VendaPojo> vendasPojo) throws IOException {
		Calendar c = Calendar.getInstance();
		
		double precoTotalVenda = 0;
		
		String nomeArquivo = "relatorioVendas_Dia_" + c.get(Calendar.DAY_OF_MONTH) + ".txt";
			
		FileWriter writer = new FileWriter(nomeArquivo);
		
		for (VendaPojo vendaPojo : vendasPojo) {
			System.out.printf(
					"Codigo de barra: %d | Quantidade: %d | Valor unitario: %.2f | Valor Total: %.2f |\n",
					vendaPojo.getCodigoBarra(), vendaPojo.getQuantidade(), vendaPojo.getValorUnitario(), vendaPojo.getValorTotal());
			
			precoTotalVenda += vendaPojo.getValorTotal();		
			
			writer.append(String.format("Codigo de barra: %d | Quantidade: %d | Valor unitario: %.2f | Valor Total: %.2f |\n",
					vendaPojo.getCodigoBarra(), vendaPojo.getQuantidade(), vendaPojo.getValorUnitario(), vendaPojo.getValorTotal()));
		}
		
		System.out.println("\n\nTotal do dia: R$" + precoTotalVenda);	
		
		writer.append(String.format("\n\nTotal do dia: R$" + precoTotalVenda));
		
		System.out.printf("\n%s criado com sucesso!\n", nomeArquivo);
		
		writer.close();
	}
	
	public static void buscarPecaPorNome(Scanner teclado) {
		teclado.nextLine();
		System.out.print("Digite o nome que deseja buscar: ");
		String nome = teclado.nextLine();

		PecaDao pecaDao = new PecaDao();
		List<PecaPojo> pecasPojo = pecaDao.buscarPecasPorNomeBD(nome);

		imprimirPecas(pecasPojo);
	}

	public static void buscarPecaPorModelo(Scanner teclado) {
		teclado.nextLine();

		System.out.print("Digite o modelo: ");
		String modelo = teclado.nextLine();

		PecaDao pecaDao = new PecaDao();
		List<PecaPojo> pecasPojo = pecaDao.buscarPecasPorModeloBD(modelo);

		imprimirPecas(pecasPojo);
	}

	public static void buscarPecaPorCategoria(Scanner teclado) {
		teclado.nextLine();

		System.out.println(CATEGORIA_EXISTE);

		System.out.print("Digite uma categoria existente: ");
		String categoria = teclado.nextLine();

		PecaDao pecaDao = new PecaDao();
		List<PecaPojo> pecasPojo = pecaDao.buscarPecasPorCategoriaBD(categoria);

		imprimirPecas(pecasPojo);
	}

	public static void removerPecaPorCodigoBarra(Scanner teclado) {
		System.out.print("Digite o codigo de barra que deseja remover: ");
		int codigoBarra = teclado.nextInt();

		PecaDao pecaDao = new PecaDao();

		if (pecaDao.excluirPecaBD(codigoBarra)) {
			System.out.println("Pe�a removida com sucesso");
		}
	}

	public static List<VendaPojo> realizarVenda(Scanner teclado, List<VendaPojo> listaVendas) {
		PecaDao pecaDao = new PecaDao();
		PecaPojo pecaPojo = new PecaPojo();

		System.out.print("Digite o codigo de barra: ");
		int codigoBarra = teclado.nextInt();

		pecaPojo = pecaDao.buscarPecaPorCodigoBarraBD(codigoBarra);

		while (pecaPojo.getCodigoBarra() == 0) {
			System.out.print("Ops.. Codigo de barra inexistente, digite novamente: ");
			codigoBarra = teclado.nextInt();

			pecaPojo = pecaDao.buscarPecaPorCodigoBarraBD(codigoBarra);
		}

		System.out.print("Digite a quantidade: ");
		int quantidade = teclado.nextInt();

		while (pecaPojo.getQuantidadeEstoque() < quantidade) {
			System.out.print("Ops.. Quantidade superior a quantidade em estoque, digite novamente: ");
			quantidade = teclado.nextInt();
		}

		pecaDao.modificarQuantidadePeca(pecaPojo, quantidade);
		pecaPojo.setPrecoVenda(quantidade * pecaPojo.getPrecoVenda());
		
		VendaPojo vendaPojo = new VendaPojo(pecaPojo.getCodigoBarra(), quantidade, quantidade * pecaPojo.getPrecoVenda(), pecaPojo.getPrecoVenda());
		

		listaVendas.add(vendaPojo);
		
		System.out.println("Venda realizada com sucesso");
		return listaVendas;
	}

	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		List<VendaPojo> listaVendas = new ArrayList<>();
		
		int opcaoMenuPrincipal = 0;
		int opcaoMenuGestaoPeca = 0;
		int opcaoMenuGestaoVenda = 0;
		do {
			System.out.println(MENU_PRINCIPAL);

			System.out.print("Escolha uma das op��es: ");
			opcaoMenuPrincipal = teclado.nextInt();

			switch (opcaoMenuPrincipal) {
			case 1:
				do {
					System.out.println(MENU_GESTAO_PECA);

					System.out.print("Escolha uma das op��es: ");
					opcaoMenuGestaoPeca = teclado.nextInt();

					switch (opcaoMenuGestaoPeca) {
					case 1:
						cadastrarPeca(teclado);
						break;

					case 2:
						buscarPecaPorCodigoBarra(teclado);
						break;

					case 3:
						buscarPecaEstoque(teclado);
						break;

					case 4:
						buscarPecaPorNome(teclado);
						break;

					case 5:
						buscarPecaPorModelo(teclado);
						break;

					case 6:
						buscarPecaPorCategoria(teclado);
						break;

					case 7:
						removerPecaPorCodigoBarra(teclado);
						break;

					case 0:

						break;
					default:
						System.out.println("Op��o invalida!");
					}
				} while (opcaoMenuGestaoPeca != 0);
				break;

			case 2:
				do {
					System.out.println(MENU_GESTAO_VENDA);

					System.out.print("Escolha uma das op��es: ");
					opcaoMenuGestaoVenda = teclado.nextInt();

					switch (opcaoMenuGestaoVenda) {
					case 1:
						listaVendas = realizarVenda(teclado, listaVendas);
						break;

					case 2:
						imprimirRelatorioVendas(listaVendas);
						break;

					case 0:

						break;
					default:
						System.out.println("Op��o invalida!");
					}
				} while (opcaoMenuGestaoVenda != 0);
				break;

			case 0:
				System.out.println("Obrigado, volte sempre :)");
				break;

			default:
				System.out.println("Op��o invalida!");
			}
		} while (opcaoMenuPrincipal != 0);

		teclado.close();
	}

}
