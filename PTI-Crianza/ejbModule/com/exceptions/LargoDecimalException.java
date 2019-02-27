package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class LargoDecimalException extends Exception {
	
	public LargoDecimalException () {
		JOptionPane.showMessageDialog(null, "Debe ingresar un numero que no contenga mas de dos decimales", "ERROR",
				JOptionPane.WARNING_MESSAGE);
	}
}
