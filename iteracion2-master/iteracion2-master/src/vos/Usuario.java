package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario {

	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="email")
	private String email;
	
	@JsonProperty(value="rol")
	private String rol;
	
	@JsonProperty(value="id")
	private String id;
	
	
	
	public Usuario (@JsonProperty(value="nombre") String pNombre, @JsonProperty(value="email")String pEmail, @JsonProperty(value="rol") String pRol, @JsonProperty(value="id") String pId)
	{
		this.nombre = pNombre;
		this.email = pEmail;
		this.rol = pRol;
		this.id = pId;
				
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

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
