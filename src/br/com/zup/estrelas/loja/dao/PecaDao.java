package br.com.zup.estrelas.loja.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import br.com.zup.estrelas.loja.pojo.Peca;

public class PecaDao {
	private EntityManager manager;

	public PecaDao() {
		this.manager = Persistence.createEntityManagerFactory("pecas").createEntityManager();
	}

	public boolean cadastrarPecaBD(Peca peca) {
		try {
			manager.getTransaction().begin();
			manager.persist(peca);
			manager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Não foi possivel cadastrar peça " + e.getMessage());
			return false;
		}

		return true;
	}

	public Peca buscarPecaPorCodigoBarraBD(int codigoBarra) {

		Peca peca = manager.find(Peca.class, codigoBarra);

		if (peca.getCodigoBarra() != 0) {
			return peca;
		} else {
			System.out.println("Codigo de barra inexistente!");
			return peca;
		}
	}

	public List<Peca> buscarPecasBD() {
		Query query = manager.createQuery("select p from Peca as p");

		return query.getResultList();
	}

	public List<Peca> buscarPecasPorNomeBD(String nome) {	
		Query query = manager.createQuery("select p from Peca as p where p.nome like CONCAT(:nome, '%')");
		
		query.setParameter("nome", nome);
		
		return query.getResultList();
	}

	public List<Peca> buscarPecasPorModeloBD(String modelo) {
		Query query = manager.createQuery("select p from Peca as p where modelo_carro = :modelo");
		
		query.setParameter("modelo", modelo);
		
		return query.getResultList();
	}

	public List<Peca> buscarPecasPorCategoriaBD(String categoria) {
		Query query = manager.createQuery("select p from Peca as p where categoria = :categoria");
		
		query.setParameter("categoria", categoria);
		
		return query.getResultList();
	}

	public boolean excluirPecaBD(int codigoBarra) {
		Peca peca = manager.find(Peca.class, codigoBarra);

		try {
			if (peca.getCodigoBarra() != 0) {
				manager.getTransaction().begin();
				manager.remove(peca);
				manager.getTransaction().commit();
			} else {
				System.out.printf("Não foi possivel excluir a peça, tente novamente\n");
				return false;
			}
		} catch (Exception e) {
			System.out.printf("Não foi possivel excluir a peça, tente novamente\n");
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean modificarQuantidadePeca(Peca peca) {
		try {
			manager.getTransaction().begin();
			manager.merge(peca);
			manager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Não foi possivel modificar a quantidade da peça " + e.getMessage());
			return false;
		}
		
		return true;
	}
}
