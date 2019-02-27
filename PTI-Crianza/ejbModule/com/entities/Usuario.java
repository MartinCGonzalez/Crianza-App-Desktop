package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USUARIO database table.
 * 
 */
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_USUARIO")
	private long idUsuario;

	private String apellido;

	private String contraseña;

	private String nombre;

	private String perfil;

	private String usuario;

	//bi-directional many-to-one association to Guachera
	@OneToMany(mappedBy="usuario")
	private List<Guachera> guacheras;

	public Usuario() {
	}
	
	public Usuario(String apellido, String contraseña, String nombre, String perfil, String usuario) {
		super();
		this.apellido = apellido;
		this.contraseña = contraseña;
		this.nombre = nombre;
		this.perfil = perfil;
		this.usuario = usuario;
	}

	public long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Guachera> getGuacheras() {
		return this.guacheras;
	}

	public void setGuacheras(List<Guachera> guacheras) {
		this.guacheras = guacheras;
	}

	public Guachera addGuachera(Guachera guachera) {
		getGuacheras().add(guachera);
		guachera.setUsuario(this);

		return guachera;
	}

	public Guachera removeGuachera(Guachera guachera) {
		getGuacheras().remove(guachera);
		guachera.setUsuario(null);

		return guachera;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", apellido=" + apellido + ", contraseña=" + contraseña + ", nombre="
				+ nombre + ", perfil=" + perfil + ", usuario=" + usuario + "]";
	}

}