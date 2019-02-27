package com.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Peso;


@Stateless
@LocalBean
public class PesoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void guardarPeso(Peso peso) throws SQLException {
		this.em.persist(peso);
	}
	
	public void actualizarPeso(Peso peso) throws SQLException {
		this.em.merge(peso);
	}

	public void borrarPeso(Long idPeso) throws SQLException {
		Peso peso = em.find(Peso.class, idPeso);
		em.remove(peso);
		em.flush();
	}

	public Peso obtenerPeso(Long idPeso) {

		return this.em.find(Peso.class, idPeso);
	}
	
	public List<Peso> obternerTodos() {
		
		TypedQuery<Peso> query = this.em.createQuery("SELECT p FROM Peso p", Peso.class);
		return query.getResultList();
	}
	
	public List<Peso> obtenerPorTernero(Long idTernero) throws SQLException {

		TypedQuery<Peso> query = em.createQuery("SELECT p FROM Peso p WHERE p.ID_TERNERO LIKE :id", Peso.class)
				.setParameter("id", idTernero);
		return query.getResultList();
	}
	
}