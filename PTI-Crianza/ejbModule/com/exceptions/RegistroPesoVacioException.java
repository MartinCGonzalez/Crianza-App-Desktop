package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class RegistroPesoVacioException extends Exception  {
	
	public RegistroPesoVacioException () {
		JOptionPane.showMessageDialog(null, "Debe registrar pesos antes para generar informe de ganancia de peso", "ERROR",
				JOptionPane.WARNING_MESSAGE);
	}
}
