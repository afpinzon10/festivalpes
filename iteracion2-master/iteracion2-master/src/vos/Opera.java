package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Opera {

	@JsonProperty(value="id_usuario")
	private String idUsuario;
	
	@JsonProperty(value="aeropuerto")
	private String aeropuerto;

	/**
	 * @param idUsuario
	 * @param aeropuerto
	 */
	public Opera(	@JsonProperty(value="id_usuario") String idUsuario,	@JsonProperty(value="aeropuerto") String aeropuerto) {
		this.idUsuario = idUsuario;
		this.aeropuerto = aeropuerto;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getAeropuerto() {
		return aeropuerto;
	}

	public void setAeropuerto(String aeropuerto) {
		this.aeropuerto = aeropuerto;
	}
}
