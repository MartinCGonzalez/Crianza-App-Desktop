package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.AlimentosDAO;
import com.entities.Alimento;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class Alimento
 */
@Stateless
@LocalBean
public class AlimentosBean implements AlimentosBeanRemote {

	/**
	 * Default constructor.
	 */

	@EJB
	AlimentosDAO alimentosDAO;

	public AlimentosBean() {
	}

	@Override
	public void alta(Alimento alimento) throws ServiciosException {

		try {
			this.alimentosDAO.guardarAlimento(alimento);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actualizar(Alimento alimento) throws ServiciosException {

		try {
			this.alimentosDAO.actualizarAlimento(alimento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void borrar(Long idAlimento) throws ServiciosException {

		try {
			this.alimentosDAO.borrarAlimento(idAlimento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Long obtenerPorId(Long id) throws ServiciosException{
		return this.alimentosDAO.obtenerporId(id);
	}

	@Override
	public List<Alimento> obtenerTodos(){
		
		return this.alimentosDAO.obternerTodos();
	}

	@Override
	public Alimento obtenerAlimento(Long idAlimento) throws ServiciosException {
		return this.alimentosDAO.obtenerAlimento(idAlimento);
	}
	
	@Override
	public Alimento obtenerPorNombre(String filtro) {
		
		return this.alimentosDAO.obtenerPorNombre(filtro);
	}


}
