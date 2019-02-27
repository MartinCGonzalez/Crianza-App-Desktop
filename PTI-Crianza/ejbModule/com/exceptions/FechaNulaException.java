package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FechaNulaException extends Exception {
	
	public FechaNulaException () {
		JOptionPane.showMessageDialog(null, "La Fecha no puede ser Nula", "ERROR",
				JOptionPane.WARNING_MESSAGE);
	}
}