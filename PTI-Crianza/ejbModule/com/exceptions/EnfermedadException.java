package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class EnfermedadException extends Exception {

	public EnfermedadException () {			

		JOptionPane.showMessageDialog(null, "C�digo de enfermedad incorrecto!", "ERROR",
				JOptionPane.WARNING_MESSAGE);
		}
}
