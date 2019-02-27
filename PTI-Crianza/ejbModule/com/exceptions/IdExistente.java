package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class IdExistente extends Exception{
	
	public IdExistente () {
		JOptionPane.showMessageDialog(null, "Alimento ya ingresado, Prueba Actualizarlo!", "ERROR",JOptionPane.WARNING_MESSAGE);
	}
}

