package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Alimento;
import com.exceptions.ServiciosException;

@Remote
public interface AlimentosBeanRemote {
	
	void alta(Alimento alimento) throws ServiciosException;
	void actualizar(Alimento alimento) throws ServiciosException;
	void borrar(Long idAlimento) throws ServiciosException;
	List<Alimento> obtenerTodos() throws ServiciosException;
	Alimento obtenerAlimento(Long id) throws ServiciosException;
	Long obtenerPorId(Long id) throws ServiciosException;
	Alimento obtenerPorNombre(String filtro);
}
