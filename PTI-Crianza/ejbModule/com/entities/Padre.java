package com.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PADRE database table.
 * 
 */
@Entity
@Table(name = "PADRE")
public class Padre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_PADRE")
	private long idPadre;

	@Column(name="NRO_CARAVANA")
	private Long nroCaravana;

//	//bi-directional many-to-one association to Ternero
//	@OneToMany(mappedBy="padre")
//	private List<Ternero> terneros;

	public Padre() {
	}

	public long getIdPadre() {
		return this.idPadre;
	}

	public void setIdPadre(long idPadre) {
		this.idPadre = idPadre;
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
//		ternero.setPadre(this);
//
//		return ternero;
//	}
//
//	public Ternero removeTernero(Ternero ternero) {
//		getTerneros().remove(ternero);
//		ternero.setPadre(null);
//
//		return ternero;
//	}

}