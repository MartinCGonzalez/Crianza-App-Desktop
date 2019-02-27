package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.EventoClinico;
import com.exceptions.ServiciosException;

@Remote
public interface EventoClinicoBeanRemote {
	
	public void guardarEventoClinico(EventoClinico e) throws ServiciosException;
	List<EventoClinico> obtenerTodos() throws ServiciosException;


	
}
