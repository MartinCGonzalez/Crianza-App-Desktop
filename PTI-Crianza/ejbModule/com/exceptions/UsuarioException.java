package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class UsuarioException extends Exception {
	
	public UsuarioException () {
		JOptionPane.showMessageDialog(null, "Usuario ya existente, por favor revise sus datos.", "ERROR",
				JOptionPane.WARNING_MESSAGE);
	}

}
