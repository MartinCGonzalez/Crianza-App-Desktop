package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ALIMENTO database table.
 * 
 */
@Entity
@Table(name = "ALIMENTO")
public class Alimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ALIMENTO")
	private Long idAlimento;

	private BigDecimal cantidad;

	@Column(name="COSTO_UNIDAD")
	private BigDecimal costoUnidad;
	
	private String nombre;

	//bi-directional many-to-one association to Unidad
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD")
	private Unidad unidad;

	//bi-directional many-to-one association to ConsumoAlimento
	@OneToMany(mappedBy="alimento")
	private List<ConsumoAlimento> consumoAlimentos;

	public Alimento() {
	}
	
	public Alimento(Long idAlimento, BigDecimal cantidad, BigDecimal costoUnidad, String nombre, Unidad unidad) {
		super();
		this.idAlimento = idAlimento;
		this.cantidad = cantidad;
		this.costoUnidad = costoUnidad;
		this.nombre = nombre;
		this.unidad = unidad;
		
	}

	public long getIdAlimento() {
		return this.idAlimento;
	}

	public void setIdAlimento(Long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCostoUnidad() {
		return this.costoUnidad;
	}

	public void setCostoUnidad(BigDecimal costoUnidad) {
		this.costoUnidad = costoUnidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public List<ConsumoAlimento> getConsumoAlimentos() {
		return this.consumoAlimentos;
	}

	public void setConsumoAlimentos(List<ConsumoAlimento> consumoAlimentos) {
		this.consumoAlimentos = consumoAlimentos;
	}

	public ConsumoAlimento addConsumoAlimento(ConsumoAlimento consumoAlimento) {
		getConsumoAlimentos().add(consumoAlimento);
		consumoAlimento.setAlimento(this);

		return consumoAlimento;
	}

	public ConsumoAlimento removeConsumoAlimento(ConsumoAlimento consumoAlimento) {
		getConsumoAlimentos().remove(consumoAlimento);
		consumoAlimento.setAlimento(null);

		return consumoAlimento;
	}

}