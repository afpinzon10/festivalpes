package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Avion {
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="economicas")
	private int sillasEconomicas;
	
	@JsonProperty(value="ejecutivas")
	private int sillasEjecutivas;
	
	
	@JsonProperty(value="capacidad")
	private int capacidad;	
	
	@JsonProperty(value="numeroserie")
	private int numeroSerie;
	
	@JsonProperty(value="modelo")
	private String modelo;
	
	@JsonProperty(value="anofabricacion")
	private int anofabricacion;
	
	@JsonProperty(value="marca")
	private String marca;
	
	
	
	public Avion (@JsonProperty(value="tipo") String pTipo, @JsonProperty(value="ejecutivas") int pEjecutivas, @JsonProperty(value="economicas") int pEconomicas, @JsonProperty(value="capacidad") int pCapacidad, @JsonProperty(value="modelo") String pModelo, @JsonProperty(value="marca") String pMarca, @JsonProperty(value="numeroserie") int pSerie, @JsonProperty(value="anofabricacion") int pFabricacion)
	{
		this.tipo = pTipo;
		this.sillasEconomicas = pEjecutivas;
		this.sillasEconomicas = pEconomicas;
		this.capacidad = pCapacidad;
		this.modelo  = pModelo;
		this.marca = pMarca;
		this.numeroSerie = pSerie;
		this.anofabricacion = pFabricacion;
	}

	public int getSillasEconomicas() {
		return sillasEconomicas;
	}

	public void setSillasEconomicas(int sillasEconomicas) {
		this.sillasEconomicas = sillasEconomicas;
	}

	public int getSillasEjecutivas() {
		return sillasEjecutivas;
	}

	public void setSillasEjecutivas(int sillasEjecutivas) {
		this.sillasEjecutivas = sillasEjecutivas;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(int numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public int getAnofabricacion() {
		return anofabricacion;
	}

	public void setAnofabricacion(int anofabricacion) {
		this.anofabricacion = anofabricacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
