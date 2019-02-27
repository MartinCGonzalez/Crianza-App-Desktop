package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.EnfermedadDAO;
import com.entities.Enfermedad;

/**
 * Session Bean implementation class EnfermedadBean
 */
@Stateless
@LocalBean
public class EnfermedadBean implements EnfermedadBeanRemote {

	@EJB
	private EnfermedadDAO enfermedadDAO;
	
	@Override
	public void guardadEnfermedad(Enfermedad e) {
		this.enfermedadDAO.guardadEnfermedad(e);
		
	}

	@Override
	public List<Enfermedad> obtenerTodos() {
		return this.enfermedadDAO.obtenerTodas();
	}

	@Override
	public Enfermedad obtenerEnfermedad(Long idEnfermedad) {
		return this.enfermedadDAO.obtenerEnfermedad(idEnfermedad);
	}
	

}
