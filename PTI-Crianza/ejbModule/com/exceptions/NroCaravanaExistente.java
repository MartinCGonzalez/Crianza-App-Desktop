package com.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class NroCaravanaExistente extends Exception {
	
	public NroCaravanaExistente () {
		JOptionPane.showMessageDialog(null, "Numero de Caravana ya ingresado!", "ERROR",JOptionPane.WARNING_MESSAGE);
	}

}
