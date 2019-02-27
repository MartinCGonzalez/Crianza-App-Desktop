package com.entities;

import java.io.Serializable;
import javax.persistence.*;


import java.util.List;

/**
 * The persistent class for the ENFERMEDAD database table.
 * 
 */
@Entity
@Table(name = "ENFERMEDAD")
public class Enfermedad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_ENFERMEDAD")
	private long idEnfermedad;

	@Column(name = "GRADO_GRAVEDAD")
	private Long gradoGravedad;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	// bi-directional many-to-one association to EventoClinico
	@OneToMany(mappedBy = "enfermedad")
	private List<EventoClinico> eventoClinicos;

	public Enfermedad() {
		super();
	}

	public Enfermedad(Long gradoGravedad, String nombre) {
		super();
		this.gradoGravedad = gradoGravedad;
		this.nombre = nombre;
	}

	public long getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(long idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public Long getGradoGravedad() {
		return gradoGravedad;
	}

	public void setGradoGravedad(Long gradoGravedad) {
		this.gradoGravedad = gradoGravedad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<EventoClinico> getEventoClinicos() {
		return eventoClinicos;
	}

	public void setEventoClinicos(List<EventoClinico> eventoClinicos) {
		this.eventoClinicos = eventoClinicos;
	}

}