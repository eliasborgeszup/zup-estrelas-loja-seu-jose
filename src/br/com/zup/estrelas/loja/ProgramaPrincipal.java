package br.com.zup.estrelas.loja;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import br.com.zup.estrelas.loja.dao.PecaDao;
import br.com.zup.estrelas.loja.pojo.PecaPojo;

public class ProgramaPrincipal {

	private static final String MENU_PRINCIPAL = ("======= BEM VINDO AO SISTEMA DE PEÇAS - LOJA DO SEU JOSE =======\n"
			+ "[1] - Gestão de peças\n" + "[2] - Gestão de vendas\n" + "[0] - Sair\n");

	private static final String MENU_GESTAO_PECA = ("\n[1] - Cadastrar uma nova peça\n"
			+ "[2] - Consultar peça por código de barra\n" + "[3] - Listar todas peças em estoque\n"
			+ "[4] - Listar peças por nome\n" + "[5] - Listar peças por modelo\n" + "[6] - Listar peças por categoria\n"
			+ "[7] - Remover peça do estoque\n" + "[0] - Voltar menu principal\n");

	private static final String MENU_GESTAO_VENDA = ("\n[1] - Realizar venda \n[2] - Extrair relatório de vendas\n"
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
		System.out.print("Digite o nome da peça: ");
		String nome = teclado.nextLine();

		System.out.print("Digite o modelo do carro: ");
		String modeloCarro = teclado.nextLine();

		System.out.print("Digite o nome do fabricante: ");
		String fabricante = teclado.nextLine();

		System.out.print("Digite o preço de custo: R$");
		double precoCusto = teclado.nextDouble();

		System.out.print("Digite o preço de venda: R$");
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
			System.out.println("\nPeça cadastrada com sucesso!\n");
		}
	}

	public static void buscarPecaPorCodigoBarra(Scanner teclado) {
		System.out.print("Digite o codigo de barra: ");
		int codigoBarra = teclado.nextInt();

		PecaDao pecaDao = new PecaDao();
		PecaPojo pecaPojo = pecaDao.buscarPecaPorCodigoBarraBD(codigoBarra);

		System.out.printf(
				"\nNome: %s Codigo de barra: %d Modelo: %s Fabricante: %s "
						+ "Preço Custo: R$%.2f Preço Venda: R$%.2f Quantidade: %d Categoria: %s",
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
							+ "Preço Custo: R$%.2f | Preço Venda: R$%.2f | Quantidade: %d | Categoria: %s |\n",
					pecaPojo.getNome(), pecaPojo.getCodigoBarra(), pecaPojo.getModeloCarro(), pecaPojo.getFabricante(),
					pecaPojo.getPrecoCusto(), pecaPojo.getPrecoVenda(), pecaPojo.getQuantidadeEstoque(),
					pecaPojo.getCategoria());
			System.out.println("=================================================================================");
		}
	}

	public static void imprimirRelatorioVendas(List<PecaPojo> pecasPojo) throws IOException {
		Calendar c = Calendar.getInstance();
		
		double precoTotalVenda = 0;
		
		String nomeArquivo = "relatorioVendas_Dia_" + c.get(Calendar.DAY_OF_MONTH) + ".txt";
			
		FileWriter writer = new FileWriter(nomeArquivo);
		
		for (PecaPojo pecaPojo : pecasPojo) {
			System.out.printf(
					"Codigo de barra: %d | Quantidade: %d | Valor Total: %.2f |\n",
					pecaPojo.getCodigoBarra(), pecaPojo.getQuantidadeEstoque(), pecaPojo.getPrecoVenda());
			
			precoTotalVenda += pecaPojo.getPrecoVenda();		
			
			writer.append(String.format("Codigo de barra: %d | Quantidade: %d | Valor Total: %.2f |\n",
					pecaPojo.getCodigoBarra(), pecaPojo.getQuantidadeEstoque(), pecaPojo.getPrecoVenda()));
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
			System.out.println("Peça removida com sucesso");
		}
	}

	public static List<PecaPojo> realizarVenda(Scanner teclado, List<PecaPojo> listaVendas) {
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

		pecaPojo.setQuantidadeEstoque(quantidade);
		pecaPojo.setPrecoVenda(quantidade * pecaPojo.getPrecoVenda());

		listaVendas.add(pecaPojo);
		
		System.out.println("Venda realizada com sucesso");
		return listaVendas;
	}

	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		List<PecaPojo> listaVendas = new ArrayList<>();
		
		int opcaoMenuPrincipal = 0;
		int opcaoMenuGestaoPeca = 0;
		int opcaoMenuGestaoVenda = 0;
		do {
			System.out.println(MENU_PRINCIPAL);

			System.out.print("Escolha uma das opções: ");
			opcaoMenuPrincipal = teclado.nextInt();

			switch (opcaoMenuPrincipal) {
			case 1:
				do {
					System.out.println(MENU_GESTAO_PECA);

					System.out.print("Escolha uma das opções: ");
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
						System.out.println("Opção invalida!");
					}
				} while (opcaoMenuGestaoPeca != 0);
				break;

			case 2:
				do {
					System.out.println(MENU_GESTAO_VENDA);

					System.out.print("Escolha uma das opções: ");
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
						System.out.println("Opção invalida!");
					}
				} while (opcaoMenuGestaoVenda != 0);
				break;

			case 0:
				System.out.println("Obrigado, volte sempre :)");
				break;

			default:
				System.out.println("Opção invalida!");
			}
		} while (opcaoMenuPrincipal != 0);

		teclado.close();
	}

	
}
