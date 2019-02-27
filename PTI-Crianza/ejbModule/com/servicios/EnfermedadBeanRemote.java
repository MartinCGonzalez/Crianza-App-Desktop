package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Enfermedad;
import com.exceptions.ServiciosException;

@Remote
public interface EnfermedadBeanRemote {
	public void guardadEnfermedad(Enfermedad e) throws ServiciosException;
	public List<Enfermedad> obtenerTodos() throws ServiciosException;
	Enfermedad obtenerEnfermedad(Long id) throws ServiciosException;
	
}
