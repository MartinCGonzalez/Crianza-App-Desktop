package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class GananciaPesoNegativaNulaException extends Exception {

	public GananciaPesoNegativaNulaException () {
		JOptionPane.showMessageDialog(null, "El ternero no muestra ganancia de peso frente a registro anterior, se recomienda dar una revision inmediata", "ERROR",
		JOptionPane.WARNING_MESSAGE);

	}
}
