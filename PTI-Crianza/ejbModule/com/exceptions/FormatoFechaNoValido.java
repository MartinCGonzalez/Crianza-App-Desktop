package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FormatoFechaNoValido extends Exception {
	
	public FormatoFechaNoValido () {
		JOptionPane.showMessageDialog(null, "El formato de la fecha debe ser DD/MM/YYYY", "ERROR",
				JOptionPane.WARNING_MESSAGE);
	}

}
