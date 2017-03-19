package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario {

	@JsonProperty(value="idusuario")
	private int idusuario;
	
	@JsonProperty(value="login")
	private String login;
	
	@JsonProperty(value="password")
	private String password;
	
	@JsonProperty(value="idrol")
	private int idrol;

	public Usuario(@JsonProperty(value="idusuario") int idusuario, 
			 		@JsonProperty(value="login") String login, 
			 		@JsonProperty(value="password") String password, 
			 		@JsonProperty(value="idrol") int idrol) {
		this.idusuario = idusuario;
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
