package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class NumeroNegativoException extends Exception {
	
	public NumeroNegativoException () {			
		JOptionPane.showMessageDialog(null, "El valor ingresado no puede ser negativo o igual a 0", "ERROR",
			JOptionPane.WARNING_MESSAGE);
	}
}