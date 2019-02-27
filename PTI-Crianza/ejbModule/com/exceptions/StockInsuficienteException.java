package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class StockInsuficienteException extends Exception {
	public StockInsuficienteException () {
		JOptionPane.showMessageDialog(null, "No hay suficiente stock del alimento seleccionado", "ERROR",
				JOptionPane.WARNING_MESSAGE);
	}
}
