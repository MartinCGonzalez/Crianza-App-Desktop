package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.EventoClinicoDAO;
import com.entities.EventoClinico;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class EventoClinicoBean
 */
@Stateless
@LocalBean
public class EventoClinicoBean implements EventoClinicoBeanRemote {

	@EJB
	private EventoClinicoDAO eventoClinicoDAO;

	@Override
	public void guardarEventoClinico(EventoClinico ev) throws ServiciosException {
		try {
			this.eventoClinicoDAO.guardarEventoClinico(ev);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EventoClinico> obtenerTodos() {
		 return this.eventoClinicoDAO.obtenerTodos();
	}

}
