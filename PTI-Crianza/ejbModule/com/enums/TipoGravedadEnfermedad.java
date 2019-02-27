package com.enums;

public enum TipoGravedadEnfermedad {
	UNO (1), DOS (2), TRES (3);
	
	private int numero;

	private TipoGravedadEnfermedad (int numero){
		this.numero= numero;
	}

	public int getNumero () {
		return numero;
	}
}