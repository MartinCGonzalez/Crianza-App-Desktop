package com.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Unidad;

/**
 * Session Bean implementation class UnidadDAO
 */
@Stateless
public class UnidadDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void guardarUnidad(Unidad u){
		this.em.persist(u);
		em.flush();
	}
	
	public List<Unidad> obternerTodos() {
		TypedQuery<Unidad> query = this.em.createQuery("select u from Unidad u", Unidad.class);
		return query.getResultList();
	}
	
	public Unidad obtenerUnidad(Long idUnidad) {
		return this.em.find(Unidad.class, idUnidad);
	}

}
