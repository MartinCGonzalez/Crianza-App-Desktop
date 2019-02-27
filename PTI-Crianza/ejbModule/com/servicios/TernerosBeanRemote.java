package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Peso;
import com.entities.Ternero;
import com.exceptions.ServiciosException;

@Remote
public interface TernerosBeanRemote {
	
	public void alta(Ternero ternero) throws ServiciosException;
	void agregarRegPeso(Peso peso) throws ServiciosException;
	List<Ternero> obtenerTodos() throws ServiciosException;
	void guardarDiasVida(Ternero ternero);
	Ternero obtenerporNroCaravana(Long nroCaravana) throws ServiciosException;
	Ternero obtenerTernero(Long idTernero) throws ServiciosException;
	double informeGananciaPeso(Ternero ternero) throws ServiciosException;


	
}
