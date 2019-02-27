package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.entities.ConsumoAlimento;
import com.exceptions.ServiciosException;

@Remote
public interface ConsumoAlimentosBeanRemote {
	
	void actualizar(ConsumoAlimento consumoAlimento) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	void alta(ConsumoAlimento consumoAlimento) throws ServiciosException;
	List<ConsumoAlimento> obtenerTodos() throws ServiciosException;
	List<ConsumoAlimento> obtenerPorTernero(Long idTernero) throws ServiciosException, SQLException;

}
