package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Compania {

	private ArrayList<Actor> actores;
	
	private ArrayList<Obra> obras;
	
	@JsonProperty(value="idusuario")
	private int idusuario;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="email")
	private String email;
	
	@JsonProperty(value="identificacion")
	private String identificacion;

	public Compania(ArrayList<Actor> actores, ArrayList<Obra> obras, @JsonProperty(value="idusuario") int idusuario, 
			@JsonProperty(value="nombre") String nombre, @JsonProperty(value="email") String email,
			@JsonProperty(value="identificacion") String identificacion) {
		this.actores = actores;
		this.obras = obras;
		this.idusuario = idusuario;
		this.nombre = nombre;
		this.email = email;
		this.identificacion = identificacion;
	}

	public ArrayList<Actor> getActores() {
		return actores;
	}

	public void setActores(ArrayList<Actor> actores) {
		this.actores = actores;
	}

	public ArrayList<Obra> getObras() {
		return obras;
	}

	public void setObras(ArrayList<Obra> obras) {
		this.obras = obras;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

}
