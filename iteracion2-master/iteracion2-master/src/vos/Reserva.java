package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Reserva {

	@JsonProperty(value="numero_reserva")
	private int numeroReserva;
	
	@JsonProperty(value="tipo_id_cliente")
	private String tipoIdCliente;
	
	@JsonProperty(value="id_cliente")
	private int idCliente;
	
	@JsonProperty(value="iata_vuelo")
	private String iataVuelo;
	
	@JsonProperty(value="fecha")
	private Date fecha;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="tipo_silla")
	private String tipoSilla;
	
	@JsonProperty(value="volumen")
	private double volumen;
	
	@JsonProperty(value="peso")
	private double peso;
	
	@JsonProperty(value="contenido")
	private String contenido;

	public Reserva(@JsonProperty(value="numero_reserva") int numeroReserva, @JsonProperty(value="tipo_id_cliente") String tipoIdCliente, @JsonProperty(value="id_cliente") int idCliente, @JsonProperty(value="iata_vuelo") String iataVuelo,
			@JsonProperty(value="tipo") Date fecha, @JsonProperty(value="tipo") String tipo, @JsonProperty(value="tipo_silla") String tipoSilla, @JsonProperty(value="volumen") double volumen,@JsonProperty(value="peso") double peso, 
			@JsonProperty(value="contenido") String contenido) {
		this.numeroReserva = numeroReserva;
		this.tipoIdCliente = tipoIdCliente;
		this.idCliente = idCliente;
		this.iataVuelo = iataVuelo;
		this.fecha = fecha;
		this.tipo = tipo;
		this.tipoSilla = tipoSilla;
		this.volumen = volumen;
		this.peso = peso;
		this.contenido = contenido;
	}

	public int getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(int numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	public String getTipoIdCliente() {
		return tipoIdCliente;
	}

	public void setTipoIdCliente(String tipoIdCliente) {
		this.tipoIdCliente = tipoIdCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getIataVuelo() {
		return iataVuelo;
	}

	public void setIataVuelo(String iataVuelo) {
		this.iataVuelo = iataVuelo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoSilla() {
		return tipoSilla;
	}

	public void setTipoSilla(String tipoSilla) {
		this.tipoSilla = tipoSilla;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	
	
	
}
