package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the PESO database table.
 * 
 */
@Entity
public class Peso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PESO")
	private BigDecimal idPeso;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(precision = 2, scale = 6)
	private BigDecimal peso;

	@Column(name = "TIPO_REGISTRO")
	private String tipoRegistro;

	// bi-directional many-to-one association to Ternero
	@ManyToOne
	@JoinColumn(name = "TERNERO")
	private Ternero ternero;

	@Column(name = "ID_TERNERO")
	private Long idTernero;

	public Peso() {
	}

	public Peso(Date fecha, BigDecimal peso, String tipoRegistro, Ternero ternero, Long idTernero) {
		this.fecha = fecha;
		this.peso = peso;
		this.tipoRegistro = tipoRegistro;
		this.ternero = ternero;
		this.idTernero = idTernero;
	}

	public BigDecimal getIdPeso() {
		return idPeso;
	}

	public void setIdPeso(BigDecimal idPeso) {
		this.idPeso = idPeso;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public Ternero getTernero() {
		return ternero;
	}

	public void setTernero(Ternero ternero) {
		this.ternero = ternero;
	}

	public Long getIdTernero() {
		return idTernero;
	}

	public void setIdTernero(Long idTernero) {
		this.idTernero = idTernero;
	}

	@Override
	public String toString() {
		return "Peso [idPeso=" + idPeso + ", fecha=" + fecha + ", peso=" + peso + ", tipoRegistro=" + tipoRegistro
				+ ", ternero=" + ternero + ", idTernero=" + idTernero + "]";
	}

}