package vos;

import java.sql.Date;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Viaje {
	
	@JsonProperty(value="iata")
	private String iata;
	
	@JsonProperty(value="fecha")
	private Date fecha;

	@JsonProperty(value="modelo")
	private String modelo;
	
	@JsonProperty(value="numero_serie")
	private int numeroSerie;

	@JsonProperty(value="tipo")
	private String tipo;

	
	@JsonProperty(value="capacidad")
	private double capacidadDisponible;
	
	@JsonProperty(value="economicas")
	private int sillasEconomicasDisponibles;
	
	@JsonProperty(value="ejecutivas")
	private int sillasEjecutivasdisponibles;
	
	@JsonProperty(value="acabado")
	private boolean acabado;
	
	public Viaje (@JsonProperty(value="tipo") String tipo, @JsonProperty(value="numeros_serie")int numeroSerie ,
			@JsonProperty(value="modelo") String modelo ,@JsonProperty(value="capacidad") double capacidadDisponible ,
			@JsonProperty(value="economicas") int sillasEconomicasDisponibles ,@JsonProperty(value="ejecutivas") int sillasEjecutivasdisponibles, 
			@JsonProperty(value="fecha") Date pFecha, @JsonProperty(value="iata") String iata, @JsonProperty(value="acabado") boolean acabado )
	{
		this.tipo = tipo;
		this.capacidadDisponible = capacidadDisponible;
		this.numeroSerie = numeroSerie;
		this.modelo = modelo;
		this.sillasEconomicasDisponibles = sillasEconomicasDisponibles;
		this.sillasEjecutivasdisponibles = sillasEjecutivasdisponibles;
		this.fecha = pFecha;
		this.iata = iata;
		this.acabado = acabado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public int getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(int numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getCapacidadDisponible() {
		return capacidadDisponible;
	}

	public void setCapacidadDisponible(double capacidadDisponible) {
		this.capacidadDisponible = capacidadDisponible;
	}

	public int getSillasEconomicasDisponibles() {
		return sillasEconomicasDisponibles;
	}

	public void setSillasEconomicasDisponibles(int sillasEconomicasDisponibles) {
		this.sillasEconomicasDisponibles = sillasEconomicasDisponibles;
	}

	public int getSillasEjecutivasdisponibles() {
		return sillasEjecutivasdisponibles;
	}

	public void setSillasEjecutivasdisponibles(int sillasEjecutivasdisponibles) {
		this.sillasEjecutivasdisponibles = sillasEjecutivasdisponibles;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isAcabado() {
		return acabado;
	}

	public void setAcabado(boolean acabado) {
		this.acabado = acabado;
	}
	
	
}
