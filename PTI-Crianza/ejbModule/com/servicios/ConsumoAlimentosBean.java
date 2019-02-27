package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.ConsumoAlimentosDAO;
import com.entities.ConsumoAlimento;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class ConsumoAlimento
 */
@Stateless
@LocalBean
public class ConsumoAlimentosBean implements ConsumoAlimentosBeanRemote {

    /**
     * Default constructor. 
     */
	
	@EJB
	private ConsumoAlimentosDAO consumoAlimentosDAO;

    public ConsumoAlimentosBean() {
    }

	@Override
	public void alta(ConsumoAlimento consumoAlimento) throws ServiciosException {

		try {
			this.consumoAlimentosDAO.guardar(consumoAlimento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
   
	@Override
	public void actualizar(ConsumoAlimento consumoAlimento) throws ServiciosException {
		
		try {
			this.consumoAlimentosDAO.actualizarConsumo(consumoAlimento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void borrar(Long idConsumo) throws ServiciosException {
	
		try {
			this.consumoAlimentosDAO.borrarConsumo(idConsumo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	@Override
	public List<ConsumoAlimento> obtenerTodos() {
		
		return this.consumoAlimentosDAO.obternerTodos();
	}

	@Override
	public List<ConsumoAlimento> obtenerPorTernero(Long idTernero) throws ServiciosException, SQLException {
		return this.consumoAlimentosDAO.obtenerPorTernero(idTernero);
	}
}
