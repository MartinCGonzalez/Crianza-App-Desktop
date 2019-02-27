package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CONSUMO_MEDICAMENTO database table.
 * 
 */
@Entity
@Table(name="CONSUMO_MEDICAMENTO")
@NamedQuery(name="ConsumoMedicamento.findAll", query="SELECT c FROM ConsumoMedicamento c")
public class ConsumoMedicamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_CONSUMO_MEDICAMENTO")
	private long idConsumoMedicamento;

	private Long cantidad;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Medicamento
	@ManyToOne
	@JoinColumn(name="ID_MEDICAMENTO")
	private Medicamento medicamento;

	//bi-directional many-to-one association to Ternero
	@ManyToOne
	@JoinColumn(name="ID_TERNERO")
	private Ternero ternero;

	public ConsumoMedicamento() {
	}

	public long getIdConsumoMedicamento() {
		return this.idConsumoMedicamento;
	}

	public void setIdConsumoMedicamento(long idConsumoMedicamento) {
		this.idConsumoMedicamento = idConsumoMedicamento;
	}

	public Long getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Medicamento getMedicamento() {
		return this.medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Ternero getTernero() {
		return this.ternero;
	}

	public void setTernero(Ternero ternero) {
		this.ternero = ternero;
	}

}