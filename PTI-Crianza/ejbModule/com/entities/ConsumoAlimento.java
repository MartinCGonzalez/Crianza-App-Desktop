package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CONSUMO_ALIMENTO database table.
 * 
 */
@Entity
@Table(name="CONSUMO_ALIMENTO")
@NamedQuery(name="ConsumoAlimento.findAll", query="SELECT c FROM ConsumoAlimento c")
public class ConsumoAlimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CONS_ALIMENTO")
	private long idConsAlimento;

	private BigDecimal cantidad;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Alimento
	@ManyToOne
	@JoinColumn(name="ID_ALIMENTO")
	private Alimento alimento;

	//bi-directional many-to-one association to Unidad
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD")
	private Unidad unidad;

	//bi-directional many-to-one association to Ternero
	@ManyToOne
	@JoinColumn(name="ID_TERNERO")
	private Ternero ternero;

	public ConsumoAlimento() {
	}
	
	public ConsumoAlimento(BigDecimal cantidad, Date fecha, Alimento alimento, Unidad unidad, Ternero ternero) {
		super();
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.alimento = alimento;
		this.unidad = unidad;
		this.ternero = ternero;

	}

	public long getIdConsAlimento() {
		return this.idConsAlimento;
	}

	public void setIdConsAlimento(long idConsAlimento) {
		this.idConsAlimento = idConsAlimento;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Alimento getAlimento() {
		return this.alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public Ternero getTernero() {
		return this.ternero;
	}

	public void setTernero(Ternero ternero) {
		this.ternero = ternero;
	}

}