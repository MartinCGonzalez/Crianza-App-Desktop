package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Unidad;
import com.exceptions.ServiciosException;

@Remote
public interface UnidadBeanRemote {
	public void guardarUnidad(Unidad u) throws ServiciosException;
	List<Unidad> obtenerTodos() throws ServiciosException;
	Unidad obtenerUnidad(Long idUnidad) throws ServiciosException;
}
