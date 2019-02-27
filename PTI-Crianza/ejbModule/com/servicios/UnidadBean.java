package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.UnidadDAO;
import com.entities.Unidad;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class UnidadBean
 */
@Stateless
@LocalBean
public class UnidadBean implements UnidadBeanRemote {

	@EJB
	UnidadDAO unidadDAO;
    
	@Override
	public List<Unidad> obtenerTodos() throws ServiciosException {
		return this.unidadDAO.obternerTodos();
	}
	
	@Override
	public Unidad obtenerUnidad(Long idUnidad) throws ServiciosException {

		return this.unidadDAO.obtenerUnidad(idUnidad);
	}

	@Override
	public void guardarUnidad(Unidad u) throws ServiciosException {
		this.unidadDAO.guardarUnidad(u);
		
	}

}
