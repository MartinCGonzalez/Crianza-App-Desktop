package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.entities.Peso;
import com.exceptions.ServiciosException;

@Remote
public interface PesoBeanRemote {
	public void alta(Peso peso) throws ServiciosException;

	Peso obtenerPeso(Long idPeso) throws ServiciosException;

	List<Peso> obtenerPorId(Long id) throws ServiciosException;

	List<Peso> obtenerTodos() throws ServiciosException;

	void actualizar(Peso peso) throws ServiciosException;

	void borrar(Long idPeso) throws ServiciosException;

	List<Peso> obtenerPorTernero(Long idTernero) throws ServiciosException, SQLException;
}
