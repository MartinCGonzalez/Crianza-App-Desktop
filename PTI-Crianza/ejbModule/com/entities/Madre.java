package com.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MADRE database table.
 * 
 */
@Entity
@Table(name = "MADRE")
public class Madre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_MADRE")
	private long idMadre;

	@Column(name="NRO_CARAVANA")
	private Long nroCaravana;

//	//bi-directional many-to-one association to Ternero
//	@OneToMany(mappedBy="madre")
//	private List<Ternero> terneros;

	public Madre() {
	}

	public long getIdMadre() {
		return this.idMadre;
	}

	public void setIdMadre(long idMadre) {
		this.idMadre = idMadre;
	}

	public Long getNroCaravana() {
		return this.nroCaravana;
	}

	public void setNroCaravana(Long nroCaravana) {
		this.nroCaravana = nroCaravana;
	}

//	public List<Ternero> getTerneros() {
//		return this.terneros;
//	}
//
//	public void setTerneros(List<Ternero> terneros) {
//		this.terneros = terneros;
//	}
//
//	public Ternero addTernero(Ternero ternero) {
//		getTerneros().add(ternero);
//		ternero.setMadre(this);
//
//		return ternero;
//	}
//
//	public Ternero removeTernero(Ternero ternero) {
//		getTerneros().remove(ternero);
//		ternero.setMadre(null);
//
//		return ternero;
//	}

}