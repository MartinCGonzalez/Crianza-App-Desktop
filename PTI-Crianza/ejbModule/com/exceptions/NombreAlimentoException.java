package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")

public class NombreAlimentoException extends Exception {

	public NombreAlimentoException() {
		JOptionPane.showMessageDialog(null, "El nombre del alimento es incorrecto!", "ERROR",
				JOptionPane.WARNING_MESSAGE);

	}

}
