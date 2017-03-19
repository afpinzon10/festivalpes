package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Boleta {
	
	@JsonProperty(value="idboleta")
	private int idboleta;
	
	@JsonProperty(value="localidad")
	private int localidad;
	
	@JsonProperty(value="precio")
	private int precio;
	
	@JsonProperty(value="fila")
	private int fila;
	
	@JsonProperty(value="silla")
	private int silla;
	
	@JsonProperty(value="idfuncion")
	private int idfuncion;
	
	@JsonProperty(value="idusuario")
	private int idusuario;

	public Boleta(@JsonProperty(value="idboleta") int idboleta, @JsonProperty(value="localidad") int localidad, @JsonProperty(value="precio") int precio, 
			@JsonProperty(value="fila") int fila, @JsonProperty(value="silla") int silla, @JsonProperty(value="idfuncion") int idfuncion, 
			@JsonProperty(value="idusuario") int idusuario) {
		this.idboleta = idboleta;
		this.localidad = localidad;
		this.precio = precio;
		this.fila = fila;
		this.silla = silla;
		this.idfuncion = idfuncion;
		this.idusuario = idusuario;
	}

	public int getIdboleta() {
		return idboleta;
	}

	public void setIdboleta(int idboleta) {
		this.idboleta = idboleta;
	}

	public int getLocalidad() {
		return localidad;
	}

	public void setLocalidad(int localidad) {
		this.localidad = localidad;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getSilla() {
		return silla;
	}

	public void setSilla(int silla) {
		this.silla = silla;
	}

	public int getIdfuncion() {
		return idfuncion;
	}

	public void setIdfuncion(int idfuncion) {
		this.idfuncion = idfuncion;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	
}
