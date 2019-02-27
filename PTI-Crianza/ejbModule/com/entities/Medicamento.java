package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MEDICAMENTO database table.
 * 
 */
@Entity
@Table(name = "MEDICAMENTO")
public class Medicamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_MEDICAMENTO")
	private long idMedicamento;

	private Long costo;

	private String dosis;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_DESDE")
	private Date fechaDesde;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_HASTA")
	private Date fechaHasta;

	private String nombre;

	//bi-directional many-to-one association to ConsumoMedicamento
	@OneToMany(mappedBy="medicamento")
	private List<ConsumoMedicamento> consumoMedicamentos;

	public Medicamento() {
	}

	public long getIdMedicamento() {
		return this.idMedicamento;
	}

	public void setIdMedicamento(long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public Long getCosto() {
		return this.costo;
	}

	public void setCosto(Long costo) {
		this.costo = costo;
	}

	public String getDosis() {
		return this.dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ConsumoMedicamento> getConsumoMedicamentos() {
		return this.consumoMedicamentos;
	}

	public void setConsumoMedicamentos(List<ConsumoMedicamento> consumoMedicamentos) {
		this.consumoMedicamentos = consumoMedicamentos;
	}

	public ConsumoMedicamento addConsumoMedicamento(ConsumoMedicamento consumoMedicamento) {
		getConsumoMedicamentos().add(consumoMedicamento);
		consumoMedicamento.setMedicamento(this);

		return consumoMedicamento;
	}

	public ConsumoMedicamento removeConsumoMedicamento(ConsumoMedicamento consumoMedicamento) {
		getConsumoMedicamentos().remove(consumoMedicamento);
		consumoMedicamento.setMedicamento(null);

		return consumoMedicamento;
	}

}