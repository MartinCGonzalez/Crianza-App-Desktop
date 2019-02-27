package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TERNERO database table.
 * 
 */
@Entity
@Table(name = "TERNERO")
public class Ternero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TERNERO")
	private long idTernero;

	@Column(name="CAUSA_MUERTE")
	private String causaMuerte;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_BAJA")
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_MUERTE")
	private Date fechaMuerte;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_NAC")
	private Date fechaNac;

	@Column(name="MOTIVO_BAJA")
	private String motivoBaja;

	@Column(name="NRO_CARAVANA", nullable = false, unique = true)
	private Long nroCaravana;

	private String parto;

	@Column(name="PESO_NAC")
	private BigDecimal pesoNac;

	private String raza;
	

	//bi-directional many-to-one association to ConsumoAlimento
	@OneToMany(mappedBy="ternero")
	private List<ConsumoAlimento> consumoAlimentos;

	//bi-directional many-to-one association to ConsumoMedicamento
	@OneToMany(mappedBy="ternero")
	private List<ConsumoMedicamento> consumoMedicamentos;

	//bi-directional many-to-one association to EventoClinico
	@OneToMany(mappedBy="ternero")
	private List<EventoClinico> eventoClinicos;

	//bi-directional many-to-one association to Peso
	@OneToMany(mappedBy="ternero", fetch=FetchType.EAGER)
	private List<Peso> listaRegPeso;

	//bi-directional many-to-one association to Guachera
	
//	@JoinColumn(name="ID_GUACHERA")
	@Column(name="ID_GUACHERA")
	private Long  Guachera;

	//bi-directional many-to-one association to Madre
	
//	@JoinColumn(name="ID_MADRE")
	@Column(name="ID_MADRE")
	private Long Madre;

	//bi-directional many-to-one association to Padre
	
//	@JoinColumn(name="ID_PADRE")
	@Column(name="ID_PADRE")
	private Long Padre;
	
	public Ternero(Long diasVida) {
		super();
	}
	
	public Ternero( BigDecimal pesoNac,Date fechaNac, Long nroCaravana, String parto, String raza, Long Guachera, Long Madre,
			Long Padre) {
		super();
		this.fechaNac = fechaNac;
		this.pesoNac = pesoNac;
		this.nroCaravana = nroCaravana;
		this.parto = parto;
		this.raza = raza;
		this.Guachera = Guachera;
		this.Madre = Madre;
		this.Padre = Padre;
	}
	
	public Ternero() {
		super();
	}
	

	public long getIdTernero() {
		return idTernero;
	}

	public void setIdTernero(long idTernero) {
		this.idTernero = idTernero;
	}

	public String getCausaMuerte() {
		return causaMuerte;
	}

	public void setCausaMuerte(String causaMuerte) {
		this.causaMuerte = causaMuerte;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaMuerte() {
		return fechaMuerte;
	}

	public void setFechaMuerte(Date fechaMuerte) {
		this.fechaMuerte = fechaMuerte;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getMotivoBaja() {
		return motivoBaja;
	}

	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
	}

	public Long getNroCaravana() {
		return nroCaravana;
	}

	public void setNroCaravana(Long nroCaravana) {
		this.nroCaravana = nroCaravana;
	}

	public String getParto() {
		return parto;
	}

	public void setParto(String parto) {
		this.parto = parto;
	}

	public BigDecimal getPesoNac() {
		return pesoNac;
	}

	public void setPesoNac(BigDecimal pesoNac) {
		this.pesoNac = pesoNac;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public List<ConsumoAlimento> getConsumoAlimentos() {
		return consumoAlimentos;
	}

	public void setConsumoAlimentos(List<ConsumoAlimento> consumoAlimentos) {
		this.consumoAlimentos = consumoAlimentos;
	}

	public List<ConsumoMedicamento> getConsumoMedicamentos() {
		return consumoMedicamentos;
	}

	public void setConsumoMedicamentos(List<ConsumoMedicamento> consumoMedicamentos) {
		this.consumoMedicamentos = consumoMedicamentos;
	}

	public List<EventoClinico> getEventoClinicos() {
		return eventoClinicos;
	}

	public void setEventoClinicos(List<EventoClinico> eventoClinicos) {
		this.eventoClinicos = eventoClinicos;
	}

	public List<Peso> getListaRegPeso() {
		return listaRegPeso;
	}

	public void setListaRegPeso(List<Peso> listaRegPeso) {
		this.listaRegPeso = listaRegPeso;
	}

	public Long getGuachera() {
		return Guachera;
	}

	public void setGuachera(Long guachera) {
		Guachera = guachera;
	}

	public Long getMadre() {
		return Madre;
	}

	public void setMadre(Long madre) {
		Madre = madre;
	}

	public Long getPadre() {
		return Padre;
	}

	public void setPadre(Long padre) {
		Padre = padre;
	}
		
	public int getLast() {

		listaRegPeso.get(listaRegPeso.size() - 1);

		return 0;

	}

}