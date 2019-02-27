package com.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import com.entities.Peso;
import com.entities.Ternero;
import com.exceptions.ServiciosException;

@Stateless
@LocalBean
public class TernerosDAO {

	@PersistenceContext
	private EntityManager em;

	public void guardarTernero(Ternero ternero) throws SQLException {
		this.em.persist(ternero);
		em.flush();
	}

	public void agregarRegPeso(Peso peso) {
		this.em.persist(peso);
	}

	public void guardarDiasVida(Ternero ternero)throws ServiciosException {	 
		try {
		em.merge(ternero);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo actualizar la carrera");
		}
	}

	public Ternero obtenerTernero(Long idTernero) {
		return this.em.find(Ternero.class, idTernero);
	}
	
	public List<Ternero> obtenerTodos() throws ServiciosException {
		TypedQuery<Ternero> query = this.em.createQuery("select t from Ternero t", Ternero.class);
		return query.getResultList();
	}
	
	public List<Ternero> obtenerPorId(Long id) throws ServiciosException {
		TypedQuery<Ternero> query = em.createQuery("SELECT t FROM TERNERO t WHERE t.id LIKE :id", Ternero.class)
				.setParameter("ID", id);
		return query.getResultList();
	}
	
	public Ternero obtenerporNroCaravana(Long nroCaravana) {		
	
		try {
		Query q = em.createQuery("SELECT t FROM Ternero t WHERE t.nroCaravana LIKE :nroCaravana",Ternero.class);
				q.setParameter("nroCaravana", nroCaravana);
		
		return  (Ternero) q.getSingleResult();

	    } catch(NoResultException e) {
	        return null;
	    }
	}
	

	public double calcularDifPeso(Ternero ternero) throws ServiciosException {

		double difPeso;

		int posPenultimo = ternero.getListaRegPeso().size() - 2;
		Peso penultimo1 = ternero.getListaRegPeso().get(posPenultimo);

		int ultimo = ternero.getListaRegPeso().size() - 1;
		Peso ultimo1 = ternero.getListaRegPeso().get(ultimo);

		double urp = ultimo1.getPeso().doubleValue();
		double pp = penultimo1.getPeso().doubleValue();

		difPeso = urp - pp;

		return difPeso;
	}
}
