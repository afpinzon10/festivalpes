package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.*;

public class Aerolinea {

	
	
	@JsonProperty(value="iata")
	private String IATA;
	
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="pais")
	private String paisOrigen;
	
	
	public Aerolinea (@JsonProperty(value="iata") String pIATA, @JsonProperty(value="nombre") String pNombre, @JsonProperty(value="pais") String pOrigen )
	{
		this.IATA = pIATA;
		this.nombre = pNombre;
		this.paisOrigen = pOrigen;
	}


	public String getIATA() {
		return IATA;
	}


	public void setIATA(String iATA) {
		IATA = iATA;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getPaisOrigen() {
		return paisOrigen;
	}


	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}
	
	
	
	
	
	
}
