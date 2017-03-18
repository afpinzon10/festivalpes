package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class RFC7y8 {

	
	@JsonProperty(value="aeropuerto")
	private String aeropuerto;
	
	@JsonProperty(value="hora_llegada")
	private String horaLlegada;
	
	@JsonProperty(value="hora_salida")
	private String horaSalida;
	
	@JsonProperty(value="fecha_inicial")
	private String fechaInicial;
	
	@JsonProperty(value="fecha_final")
	private String fechaFinal;
	
	@JsonProperty(value="tipo_aeronave")
	private String tipoAeronave;
	
	@JsonProperty(value="aerolinea")
	private String aerolinea;
	
	@JsonProperty(value="arribos")
	private List<Viaje> arribos;
	
	@JsonProperty(value="salidas")
	private List<Viaje> salidas;
	
	
	public RFC7y8(@JsonProperty(value="aeropuerto") String aeropuerto, @JsonProperty(value="hora_llegada") String horaLlegada,
			@JsonProperty(value="hora_salida") String horaSalida, @JsonProperty(value="fecha_inicial") String fechaInicial,
			@JsonProperty(value="fecha_final") String fechaFinal, @JsonProperty(value="tipo_aeronave") String tipoAeronave,
			@JsonProperty(value="aerolinea")  String aerolinea ) {
		this.aeropuerto = aeropuerto;
		this.horaLlegada = horaLlegada;
		this.horaSalida = horaSalida;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.tipoAeronave= tipoAeronave;
		this.aerolinea = aerolinea;
	}
	
	
	public List<Viaje> getArribos() {
		return arribos;
	}

	public void setArribos(List<Viaje> arribos) {
		this.arribos = arribos;
	}

	public List<Viaje> getSalidas() {
		return salidas;
	}

	public void setSalidas(List<Viaje> salidas) {
		this.salidas = salidas;
	}

	public String getAeropuerto() {
		return aeropuerto;
	}

	public void setAeropuerto(String aeropuerto) {
		this.aeropuerto = aeropuerto;
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

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}


	public String getTipoAeronave() {
		return tipoAeronave;
	}


	public void setTipoAeronave(String tipoAeronave) {
		this.tipoAeronave = tipoAeronave;
	}


	public String getAerolinea() {
		return aerolinea;
	}


	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}
	
	
	
	
	
	
	
}
