package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the UNIDAD database table.
 * 
 */
@Entity
@Table(name = "UNIDAD")
public class Unidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_UNIDAD")
	private long idUnidad;

	@Column(name = "UNIDAD")
	private String unidad;

	// bi-directional many-to-one association to Alimento
	@OneToMany
	@JoinColumn(name="ID_ALIMENTO")
	private List<Alimento> alimentos;

	// bi-directional many-to-one association to ConsumoAlimento
	@OneToMany(mappedBy = "unidad")
	private List<ConsumoAlimento> consumoAlimentos;

	public Unidad() {
	}

	public Unidad(long idUnidad, String unidad, List<Alimento> alimentos, List<ConsumoAlimento> consumoAlimentos) {
		super();
		this.idUnidad = idUnidad;
		this.unidad = unidad;
		this.alimentos = alimentos;
		this.consumoAlimentos = consumoAlimentos;
	}

	public Unidad(Long idUnidad, String unidad) {
		super();
		this.idUnidad = idUnidad;
		this.unidad = unidad;
	}

	public long getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(long idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	public List<ConsumoAlimento> getConsumoAlimentos() {
		return consumoAlimentos;
	}

	public void setConsumoAlimentos(List<ConsumoAlimento> consumoAlimentos) {
		this.consumoAlimentos = consumoAlimentos;
	}

}