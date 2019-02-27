package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class LargoNumeroException extends Exception {

	public LargoNumeroException () {
		JOptionPane.showMessageDialog(null, "El largo del numero ingresado excede lo permitido", "ERROR",
				JOptionPane.WARNING_MESSAGE);
	}
}
