package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EVENTO_CLINICO database table.
 * 
 */
@Entity
@Table(name="EVENTO_CLINICO")
public class EventoClinico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_EVENTO_CLINICO")
	private long idEventoClinico;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_DESDE")
	private Date fechaDesde;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_HASTA")
	private Date fechaHasta;

	private String observacion;

	//bi-directional many-to-one association to Enfermedad
	@ManyToOne
	@JoinColumn(name="ID_ENFERMEDAD")
	private Enfermedad enfermedad;

	//bi-directional many-to-one association to Ternero
	@ManyToOne
	@JoinColumn(name="ID_TERNERO")
	private Ternero ternero;

	public EventoClinico() {
		super();
	}

	public EventoClinico(Date fechaDesde, Enfermedad enfermedad, Ternero ternero) {
		super();
		this.fechaDesde = fechaDesde;
		this.enfermedad = enfermedad;
		this.ternero = ternero;
	}
	
	public EventoClinico(Date fechaDesde, Enfermedad enfermedad, Ternero ternero, Date fechaHasta,
			String observacion) {
		super();
		this.fechaDesde = fechaDesde;
		this.enfermedad = enfermedad;
		this.ternero = ternero;
		this.fechaHasta = fechaHasta;
		this.observacion = observacion;
	}

	public long getIdEventoClinico() {
		return this.idEventoClinico;
	}

	public void setIdEventoClinico(long idEventoClinico) {
		this.idEventoClinico = idEventoClinico;
	}

	public Date getFechaDesde() {
		return this.fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return this.fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Enfermedad getEnfermedad() {
		return this.enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Ternero getTernero() {
		return this.ternero;
	}

	public void setTernero(Ternero ternero) {
		this.ternero = ternero;
	}

}