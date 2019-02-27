package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ObservacionException extends Exception {
	
	public ObservacionException () {
		JOptionPane.showMessageDialog(null, "No igresar mas de 250 caracteres!", "ERROR",
				JOptionPane.WARNING_MESSAGE);
	}

}
