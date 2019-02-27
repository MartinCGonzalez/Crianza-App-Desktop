package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FechaFuturoException extends Exception {
	
	public FechaFuturoException () {

	JOptionPane.showMessageDialog(null, "No puede seleccionar fecha a futuro!", "ERROR",
			JOptionPane.WARNING_MESSAGE);
}

}
