package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Vuelo {
	
	
	@JsonProperty(value="densidad")
	private int tarifaDensidad;
	
	@JsonProperty(value="iata")
	private String IATA;
	
	@JsonProperty(value="frecuencia")
	private String frecuencia;
	
	@JsonProperty(value="llegada")
	private String horaLlegada;
	
	@JsonProperty(value="salida")
	private String horaSalida;
	
	@JsonProperty(value="distancia")
	private double distacia;
	
	@JsonProperty(value="duracion")
	private String duracion;
	
	@JsonProperty(value="ejecutiva")
	private int tarifaEjecutiva;
	
	@JsonProperty(value="economica")
	private int tarifaEconomica;
	
	
	private String origen;
	
	private String destino;

	
	public Vuelo (@JsonProperty(value="economica") int tarifaEconomica, @JsonProperty(value="ejecutiva") int tarifaEjecutiva, @JsonProperty(value="densidad") int tarifaDensidad, @JsonProperty(value="iata") String pIATA, @JsonProperty(value="frecuencia") String pFrecuencia,
			@JsonProperty(value="llegada") String pLlegada, @JsonProperty(value="salida")String pSalida, @JsonProperty(value="distancia") double pDistancia,
			@JsonProperty(value="duracion") String pDuracion, String pOrigen, String pDestino)
	{
		this.tarifaDensidad = tarifaDensidad;
		this.tarifaEconomica = tarifaEconomica;
		this.tarifaEjecutiva = tarifaEjecutiva;
		this.IATA = pIATA;
		this.frecuencia = pFrecuencia;
		this.horaLlegada = pLlegada;
		this.horaSalida = pSalida;
		this.distacia = pDistancia;
		this.duracion = pDuracion;
		this.origen = pOrigen;
		this.destino = pDestino;
	}


	public String getIATA() {
		return IATA;
	}


	public void setIATA(String iATA) {
		IATA = iATA;
	}


	public String getFrecuencia() {
		return frecuencia;
	}


	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}


	public String getHoraLlegada() {
		return horaLlegada;
	}


	public void setHoraLlegada(String horaLlegada) {
		this.horaLlegada = horaLlegada;
	}


	public String getHoraSalida() {
		return horaSalida;
	}


	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}


	public double getDistacia() {
		return distacia;
	}


	public void setDistacia(double distacia) {
		this.distacia = distacia;
	}


	public String getDuracion() {
		return duracion;
	}


	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}


	public int getTarifaDensidad() {
		return tarifaDensidad;
	}


	public void setTarifaDensidad(int tarifaDensidad) {
		this.tarifaDensidad = tarifaDensidad;
	}


	public int getTarifaEjecutiva() {
		return tarifaEjecutiva;
	}


	public void setTarifaEjecutiva(int tarifaEjecutiva) {
		this.tarifaEjecutiva = tarifaEjecutiva;
	}


	public int getTarifaEconomica() {
		return tarifaEconomica;
	}


	public void setTarifaEconomica(int tarifaEconomica) {
		this.tarifaEconomica = tarifaEconomica;
	}


	public String getOrigen() {
		return origen;
	}


	public void setOrigen(String origen) {
		this.origen = origen;
	}


	public String getDestino() {
		return destino;
	}


	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	
	
}
