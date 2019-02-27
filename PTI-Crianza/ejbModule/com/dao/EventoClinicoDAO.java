package com.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.EventoClinico;

/**
 * Session Bean implementation class EventoClinicoDAO
 */
@Stateless
@LocalBean
public class EventoClinicoDAO {

    @PersistenceContext
    private EntityManager em;
    
    public void guardarEventoClinico(EventoClinico e) throws SQLException{
    	this.em.persist(e);
    }
    
	public List<EventoClinico> obtenerTodos() {
		TypedQuery<EventoClinico> query = this.em.createQuery("SELECT ec FROM EventoClinico ec", EventoClinico.class);
		return query.getResultList();
	}
	
}
