package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario {

	@JsonProperty(value="idusuario")
	private int idusuario;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="apellido")
	private String apellido;
	
	@JsonProperty(value="email")
	private String email;
	
	@JsonProperty(value="identificacion")
	private String identificacion;
	
	@JsonProperty(value="login")
	private String login;
	
	@JsonProperty(value="password")
	private String password;
	
	@JsonProperty(value="idrol")
	private int idrol;

	public Usuario(@JsonProperty(value="idusuario") int idusuario, @JsonProperty(value="nombre") String nombre, 
			@JsonProperty(value="apellido") String apellido, @JsonProperty(value="email") String email, 
			@JsonProperty(value="identificacion") String identificacion, @JsonProperty(value="login") String login, 
			@JsonProperty(value="password") String password, @JsonProperty(value="idrol") int idrol) {
		this.idusuario = idusuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.identificacion = identificacion;
		this.login = login;
		this.password = password;
		this.idrol = idrol;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIdrol() {
		return idrol;
	}

	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}
	
}
