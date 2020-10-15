# Projeto (zup-estrelas-loja-seu-jose)
	Sistema de gerenciamento de estoque e vendas de pecas
	
	
# [1] Menu Gestao de Pecas 
 - **[1.1] - Cadastrar uma nova peca:** Nesta funcionalidade criamos um objeto peca e fazemos a interacao deste objeto com um metodo na pecaDao (cadastrarPecaBD) que e responsavel em inserir no Banco de Dados 
 
 - **[1.2] - Consultar peca por codigo de barra:** Nesta funcionalidade criamos um metodo onde buscamos a informacao codigoBarra e fazemos a interacao desta variavel com um metodo na pecaDao (buscarPecaPorCodigoBarraBD) que e responsavel por buscar uma peca atraves do codigo de barra
 
 - **[1.3] - Listar todas pecas em estoque:** Nesta funcionalidade criamos um metodo que busca uma lista (List<>) de pecas (buscarPecasBD)
 
 - **[1.4] - Listar pecas por nome:** Nesta funcionalidade criamos um metodo que busca uma lista (List<>) de pecas por nome inicial (buscarPecasPorNomeBD)
 
 - **[1.5] - Listar pecas por modelo:** Nesta funcionalidade criamos um metodo que busca uma lista (List<>) de pecas por modelo (buscarPecasPorModeloBD)
 
 - **[1.6] - Listar pecas por categoria:** Nesta funcionalidade criamos um metodo que busca uma lista (List<>) de pecas por categoria (buscarPecasPorCategoriaBD)
 
 - **[1.7] - Remover peca do estoque:** Nesta funcionalidade criamos um metodo (excluirPecaBD) que remove uma peca atraves do codigo de barra
 
# [2] Menu Gestao de Vendas

- **[2.1] - Realizar venda** Nesta funcionalidade criamos um metodo (realizarVenda) que faz um update em peca e guarda uma lista (List<>) a venda realizada, alem de criar uma validacao de quantidade com o metodo (buscarPecaPorCodigoBarraBD)

- **[2.2] - Extrair relatorio de vendas** Nesta funcionalidade criamos um metodo de imprimir e armazenar em um arquivo o relatorio do dia