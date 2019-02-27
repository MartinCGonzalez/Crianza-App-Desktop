package com.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GUACHERA database table.
 * 
 */
@Entity
@Table(name = "GUACHERA")
public class Guachera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_GUACHERA")
	private Long idGuachera;

	@Column(columnDefinition="CHAR(12)")
	private String nombre;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

//	//bi-directional many-to-one association to Ternero
//	@OneToMany(mappedBy="guachera")
//	private List<Ternero> terneros;

	public Guachera() {
	}

	public long getIdGuachera() {
		return this.idGuachera;
	}

	public void setIdGuachera(long idGuachera) {
		this.idGuachera = idGuachera;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
//		ternero.setGuachera(this);
//
//		return ternero;
//	}
//
//	public Ternero removeTernero(Ternero ternero) {
//		getTerneros().remove(ternero);
//		ternero.setGuachera(null);
//
//		return ternero;
//	}

}