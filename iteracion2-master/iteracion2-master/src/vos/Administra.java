package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Administra {

	@JsonProperty(value="id_usuario")
	private String idUsuario;
	
	@JsonProperty(value="aerolinea")
	private String aerolinea;
	
	/**
	 * @param idUsuario
	 * @param aerolinea
	 */
	public Administra(@JsonProperty(value="id_usuario") String idUsuario, @JsonProperty(value="aerolinea") String aerolinea) {
		this.idUsuario = idUsuario;
		this.aerolinea = aerolinea;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}

	
	
}
