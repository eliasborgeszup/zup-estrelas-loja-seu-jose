package br.com.zup.estrelas.loja;

import java.util.List;
import java.util.Scanner;

import br.com.zup.estrelas.loja.dao.PecaDao;
import br.com.zup.estrelas.loja.pojo.PecaPojo;

public class ProgramaPrincipal {

	private static final String MENU_PRINCIPAL = ("======= BEM VINDO AO SISTEMA DE PEÇAS =======\n"
			+ "[1] - Gestão de peças\n" + "[2] - Gestão de vendas\n" + "[0] - Sair\n");

	private static final String MENU_GESTAO_PECA = ("\n[1] - Cadastrar uma nova peça\n"
			+ "[2] - Consultar peça por código de barra\n" + "[3] - Listar todas peças em estoque\n"
			+ "[4] - Listar peças por nome\n" + "[5] - Listar peças por modelo\n" + "[6] - Listar peças por categoria\n"
			+ "[7] - Remover peça do estoque\n" + "[0] - Voltar menu principal\n");

	private static final String MENU_GESTAO_VENDA = ("\n[1] -\n" + "[0] - Voltar menu principal\n");

	public static void cadastrarPeca(Scanner teclado) {
		System.out.print("Digite o codigo de barra: ");
		int codigoBarra = teclado.nextInt();
		
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
		
		PecaPojo pecaPojo = new PecaPojo(codigoBarra, nome, modeloCarro, fabricante, precoCusto, precoVenda, quantidadeEstoque, categoria);
	
		PecaDao pecaDao = new PecaDao();
		
		if(pecaDao.cadastrarPecaBD(pecaPojo)) {
			System.out.println("\nPeça cadastrada com sucesso!\n");
		}
	}

	public static void buscarPecaPorCodigoBarra(Scanner teclado) {
		System.out.print("Digite o codigo de barra: ");
		int codigoBarra = teclado.nextInt();
		
		PecaDao pecaDao = new PecaDao();
		PecaPojo pecaPojo = pecaDao.buscarPecaPorCodigoBarraBD(codigoBarra);
				
		System.out.printf("\nNome: %s Codigo de barra: %d Modelo: %s Fabricante: %s "
				+ "Preço Custo: R$%.2f Preço Venda: R$%.2f Quantidade: %d Categoria: %s", pecaPojo.getNome(), pecaPojo.getCodigoBarra(),
				pecaPojo.getModeloCarro(), pecaPojo.getFabricante(), pecaPojo.getPrecoCusto(), pecaPojo.getPrecoVenda(),
				pecaPojo.getQuantidadeEstoque(), pecaPojo.getCategoria());
	}
	
	public static void buscarPecaEstoque(Scanner teclado) {	
		PecaDao pecaDao = new PecaDao();
		List<PecaPojo> pecasPojo = pecaDao.buscarPecasBD();
		
		imprimirPecas(pecasPojo);
	}
	
	public static void imprimirPecas(List<PecaPojo> pecasPojo) {
		for (PecaPojo pecaPojo : pecasPojo) {
			System.out.printf("\nNome: %s Codigo de barra: %d Modelo: %s Fabricante: %s "
					+ "Preço Custo: R$%.2f Preço Venda: R$%.2f Quantidade: %d Categoria: %s\n\n", pecaPojo.getNome(), pecaPojo.getCodigoBarra(),
					pecaPojo.getModeloCarro(), pecaPojo.getFabricante(), pecaPojo.getPrecoCusto(), pecaPojo.getPrecoVenda(),
					pecaPojo.getQuantidadeEstoque(), pecaPojo.getCategoria());
			System.out.println("=================================================================================");
		}
	}
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
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

						break;
						
					case 5:

						break;
						
					case 6:

						break;
						
					case 7:

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
