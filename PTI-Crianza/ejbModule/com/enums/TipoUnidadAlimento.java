package com.enums;

public enum TipoUnidadAlimento {
	LITRO("LITRO",2),KILO("KILO",1);
	
	private int id;
	private String nombre;
	
	private TipoUnidadAlimento(String nombre, int id) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
