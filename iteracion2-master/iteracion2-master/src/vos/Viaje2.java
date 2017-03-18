package vos;


import java.sql.Date;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Viaje2 {
	
	@JsonProperty(value="iata")
	private String iata;
	
	@JsonProperty(value="fecha")
	private Date fecha;

	@JsonProperty(value="modelo")
	private String modelo;
	
	@JsonProperty(value="numero_serie")
	private int numero_serie;

	@JsonProperty(value="tipo")
	private String tipo;

	@JsonProperty(value="capacidad")
	private double capacidad;
	
	@JsonProperty(value="economicas")
	private int economicas;
	
	@JsonProperty(value="ejecutivas")
	private int ejecutivas;
	
	@JsonProperty(value="acabado")
	private boolean acabado;
	
	@JsonProperty(value="viajeros")
	private int viajeros;
	
	@JsonProperty(value="capacidad_usada")
	private double capacidad_usada;
	
	public Viaje2 ( String iata, Date pFecha, String modelo, int numeroSerie, String tipo,
			double capacidadDisponible, int sillasEconomicasDisponibles, int sillasEjecutivas,
			boolean acabado, int viajeros, double capacidadUsada)
	{
		this.iata = iata;
		this.tipo = tipo;
		this.capacidad = capacidadDisponible;
		this.numero_serie = numeroSerie;
		this.modelo = modelo;
		this.economicas = sillasEconomicasDisponibles;
		this.ejecutivas = sillasEjecutivas;
		this.fecha = pFecha;
		this.acabado = acabado;
		int sillasDisponibles = sillasEconomicasDisponibles+sillasEjecutivas;
		this.viajeros = viajeros - sillasDisponibles;
		this.capacidad_usada = capacidadUsada - capacidadDisponible;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getNumero_serie() {
		return numero_serie;
	}

	public void setNumero_serie(int numero_serie) {
		this.numero_serie = numero_serie;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}

	public int getEconomicas() {
		return economicas;
	}

	public void setEconomicas(int economicas) {
		this.economicas = economicas;
	}

	public int getEjecutivas() {
		return ejecutivas;
	}

	public void setEjecutivas(int ejecutivas) {
		this.ejecutivas = ejecutivas;
	}

	public boolean isAcabado() {
		return acabado;
	}

	public void setAcabado(boolean acabado) {
		this.acabado = acabado;
	}

	public int getViajeros() {
		return viajeros;
	}

	public void setViajeros(int viajeros) {
		this.viajeros = viajeros;
	}

	public double getCapacidad_usada() {
		return capacidad_usada;
	}

	public void setCapacidad_usada(double capacidad_usada) {
		this.capacidad_usada = capacidad_usada;
	}

	
	
}
