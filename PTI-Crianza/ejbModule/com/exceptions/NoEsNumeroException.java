package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class NoEsNumeroException extends Exception {

	public NoEsNumeroException() {

		JOptionPane.showMessageDialog(null, "La cantidad debe de ser un numero!", "ERROR",
				JOptionPane.INFORMATION_MESSAGE);

	}
}
