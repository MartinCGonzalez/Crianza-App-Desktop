package com.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.ConsumoAlimento;


@Stateless
@LocalBean
public class ConsumoAlimentosDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void guardar(ConsumoAlimento consumoAlimento) throws SQLException {
		this.em.persist(consumoAlimento);
	}
	
	public void actualizarConsumo(ConsumoAlimento consumoAlimento) throws SQLException {
		this.em.merge(consumoAlimento);
	}

	public void borrarConsumo(Long idConsumo) throws SQLException {
		ConsumoAlimento consumo = em.find(ConsumoAlimento.class, idConsumo);
		em.remove(consumo);
		em.flush();
	}
	
	public List<ConsumoAlimento> obternerTodos() {

		TypedQuery<ConsumoAlimento> query = this.em.createQuery("SELECT c FROM ConsumoAlimento c", ConsumoAlimento.class);
		return query.getResultList();
	}
	
	public List<ConsumoAlimento> obtenerPorTernero(Long idTernero) throws SQLException {
		
		TypedQuery<ConsumoAlimento> query = em.createQuery("SELECT c FROM Consumo_Alimento c WHERE c.ID_TERNERO LIKE :id", ConsumoAlimento.class)
				.setParameter("id", idTernero);
		return query.getResultList();
	}
}
