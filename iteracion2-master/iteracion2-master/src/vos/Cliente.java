package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Cliente {

	
	private ArrayList<Reserva> reservas;
	
	@JsonProperty(value="creador")
	private String creador;
	
	@JsonProperty(value="remitente")
	private boolean remitente;
	
	@JsonProperty(value="viajero")
	private boolean viajero;
	
	@JsonProperty(value="tipo")
	private String tipoId;
	
	@JsonProperty(value="id")
	private String id;
	
	@JsonProperty(value="frecuente")
	private boolean frecuente;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	public Cliente (@JsonProperty(value="idUsuario") String idUsuario, @JsonProperty(value="remitente")int pRemitente, @JsonProperty(value="viajero")int pViajero, 
	@JsonProperty(value="tipo") String pTipo, @JsonProperty(value="id") String pId, @JsonProperty(value="frecuente") int pFrecuente,
			@JsonProperty(value="nombre") String pNombre)
	{
		this.reservas = new ArrayList<>();
		this.remitente = esTrue(pRemitente);
		this.viajero = esTrue(pViajero);
		this.tipoId = pTipo;
		this.id = pId;
		this.frecuente = esTrue(pFrecuente);
		this.nombre = pNombre;
		this.creador = idUsuario;
	}
	
	public boolean esTrue(int pParametro)
	{
		return (pParametro == 1)? true: false;
	}

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}

	public String getCreador() {
		return creador;
	}

	public void setCreador(String idUsuario) {
		this.creador = idUsuario;
	}

	public boolean isRemitente() {
		return remitente;
	}

	public void setRemitente(boolean remitente) {
		this.remitente = remitente;
	}

	public boolean isViajero() {
		return viajero;
	}

	public void setViajero(boolean viajero) {
		this.viajero = viajero;
	}

	public String getTipoId() {
		return tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isFrecuente() {
		return frecuente;
	}

	public void setFrecuente(boolean frecuente) {
		this.frecuente = frecuente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	
}
