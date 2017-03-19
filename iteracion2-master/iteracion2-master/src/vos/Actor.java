package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Actor {

	@JsonProperty(value="idartista")
	private int idartista;
	
	@JsonProperty(value="nombre")
	private int nombre;
	
	@JsonProperty(value="apellido")
	private int apellido;
	
	@JsonProperty(value="identificacion")
	private int identificacion;
	
	@JsonProperty(value="idcompania")
	private int idcompania;

	public Actor(@JsonProperty(value="idartista") int idartista, @JsonProperty(value="nombre") int nombre, @JsonProperty(value="apellido") int apellido, 
			@JsonProperty(value="identificacion") int identificacion, @JsonProperty(value="idcompania") int idcompania) {
		super();
		this.idartista = idartista;
		this.nombre = nombre;
		this.apellido = apellido;
		this.identificacion = identificacion;
		this.idcompania = idcompania;
	}

	public int getIdartista() {
		return idartista;
	}

	public void setIdartista(int idartista) {
		this.idartista = idartista;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public int getApellido() {
		return apellido;
	}

	public void setApellido(int apellido) {
		this.apellido = apellido;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	public int getIdcompania() {
		return idcompania;
	}

	public void setIdcompania(int idcompania) {
		this.idcompania = idcompania;
	}
	
	
}
