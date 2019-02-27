package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class TerneroNoVivoException extends Exception {
	
	public TerneroNoVivoException () {			

	JOptionPane.showMessageDialog(null, "El ternero no está vivo!", "ERROR",
			JOptionPane.WARNING_MESSAGE);
	}
}
