package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.TernerosDAO;
import com.entities.Peso;
import com.entities.Ternero;
import com.exceptions.ServiciosException;


@Stateless
@LocalBean
public class TernerosBean implements TernerosBeanRemote {

	
	@EJB
	TernerosDAO ternerosDAO;
	
	@Override
	public void alta(Ternero ternero) throws ServiciosException {
		try {
			this.ternerosDAO.guardarTernero(ternero);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void agregarRegPeso(Peso peso) throws ServiciosException {
		this.ternerosDAO.agregarRegPeso(peso);
	}
	
	@Override
	public void guardarDiasVida(Ternero ternero) {
		try {
			this.ternerosDAO.guardarDiasVida(ternero);
	} catch (Exception e) {
		e.printStackTrace();	
}
	}
	
	@Override
	public Ternero obtenerTernero(Long idTernero) throws ServiciosException{
		return this.ternerosDAO.obtenerTernero(idTernero);
	}
	
	@Override
	public Ternero obtenerporNroCaravana(Long nroCaravana) throws ServiciosException{
		return this.ternerosDAO.obtenerporNroCaravana(nroCaravana);
	}
	
	@Override
	public List<Ternero> obtenerTodos() throws ServiciosException {
		return this.ternerosDAO.obtenerTodos();
	}	

	public double informeGananciaPeso(Ternero ternero) throws ServiciosException {
		
		double resultado = ternerosDAO.calcularDifPeso(ternero);

		return resultado;
	}


}
